package com.example.apparelsystem.controller;

import com.example.apparelsystem.model.Sale;
import com.example.apparelsystem.repository.SaleRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CsvUploadController {

    private final SaleRepository saleRepository;

    public CsvUploadController(
            SaleRepository saleRepository
    ) {
        this.saleRepository = saleRepository;
    }

    /*
     * 04画面から送信されたCSVを読み込む。
     * この時点ではDBへ登録しない。
     * 読み込んだデータは05確認画面へ渡す。
     */
    @PostMapping("/sales/upload")
    public String uploadCsv(
            @RequestParam("csvFile") MultipartFile file,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // ファイル未選択
        if (file == null || file.isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "CSVファイルを選択してください。"
            );

            return "redirect:/sales/new";
        }

        String fileName = file.getOriginalFilename();

        // 拡張子を確認
        if (fileName == null
                || !fileName.toLowerCase().endsWith(".csv")) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "CSV形式のファイルを選択してください。"
            );

            return "redirect:/sales/new";
        }

        List<Sale> confirmSales = new ArrayList<>();

        // CSVファイル内の売上ID重複チェック用
        Set<String> csvSaleIDs = new HashSet<>();

        int lineNumber = 1;

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        file.getInputStream(),
                                        StandardCharsets.UTF_8
                                )
                        )
        ) {

            // 1行目のヘッダー
            String header = reader.readLine();

            if (header == null || header.isBlank()) {
                throw new IllegalArgumentException(
                        "CSVファイルにデータがありません。"
                );
            }

            String line;

            // 2行目以降を読み込む
            while ((line = reader.readLine()) != null) {

                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                /*
                 * 空欄の列も保持するため、第二引数に-1を指定
                 */
                String[] data = line.split(",", -1);

                if (data.length != 9) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の列数が正しくありません。"
                                    + "CSVは9列必要です。"
                    );
                }

                String saleID = data[0].trim();
                String lineID = data[1].trim();
                String acceptanceID = data[2].trim();
                String saleDateAndTimeText = data[3].trim();
                String quantityText = data[4].trim();
                String buyersAgeGroup = data[5].trim();
                String buyersGender = data[6].trim();
                String contactPersonText = data[7].trim();
                String paymentMethod = data[8].trim();

                // 空欄チェック
                if (saleID.isBlank()
                        || lineID.isBlank()
                        || acceptanceID.isBlank()
                        || saleDateAndTimeText.isBlank()
                        || quantityText.isBlank()
                        || buyersAgeGroup.isBlank()
                        || buyersGender.isBlank()
                        || contactPersonText.isBlank()
                        || paymentMethod.isBlank()) {

                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目に空欄があります。"
                    );
                }

                // IDの文字数チェック
                if (saleID.length() > 8) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の売上IDは8文字以内にしてください。"
                    );
                }

                if (lineID.length() > 8) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の明細IDは8文字以内にしてください。"
                    );
                }

                if (acceptanceID.length() > 8) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の受付IDは8文字以内にしてください。"
                    );
                }

                // 1文字項目のチェック
                if (buyersAgeGroup.length() != 1) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の購入者年代は1文字で入力してください。"
                    );
                }

                if (buyersGender.length() != 1) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の購入者性別は1文字で入力してください。"
                    );
                }

                if (paymentMethod.length() != 1) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の支払方法は1文字で入力してください。"
                    );
                }

                // 数値へ変換
                Integer quantity =
                        Integer.valueOf(quantityText);

                Integer contactPerson =
                        Integer.valueOf(contactPersonText);

                if (quantity <= 0) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の数量は1以上にしてください。"
                    );
                }

                if (contactPerson < 0) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の担当者番号は0以上にしてください。"
                    );
                }

                /*
                 * 日時を変換
                 * CSVの例：2026-10-23T10:30:00
                 */
                LocalDateTime saleDateAndTime =
                        LocalDateTime.parse(
                                saleDateAndTimeText
                        );

                // すでにDBに存在する売上IDを拒否
                if (saleRepository.existsById(saleID)) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の売上ID「"
                                    + saleID
                                    + "」は既に登録されています。"
                    );
                }

                // CSVファイル内の売上ID重複を拒否
                if (!csvSaleIDs.add(saleID)) {
                    throw new IllegalArgumentException(
                            lineNumber
                                    + "行目の売上ID「"
                                    + saleID
                                    + "」がCSV内で重複しています。"
                    );
                }

                Sale sale = new Sale(
                        saleID,
                        lineID,
                        acceptanceID,
                        saleDateAndTime,
                        quantity,
                        buyersAgeGroup,
                        buyersGender,
                        contactPerson,
                        paymentMethod
                );

                /*
                 * ここではsaveしない。
                 * 確認画面へ表示する一覧へ追加するだけ。
                 */
                confirmSales.add(sale);
            }

            if (confirmSales.isEmpty()) {
                throw new IllegalArgumentException(
                        "CSVファイルに登録対象のデータがありません。"
                );
            }

            /*
             * 05画面の登録実行ボタンを押した後も使えるように、
             * 読み込んだデータをセッションへ一時保存する。
             */
            session.setAttribute(
                    "confirmSales",
                    confirmSales
            );

            // 05確認画面へ渡す
            model.addAttribute(
                    "confirmSales",
                    confirmSales
            );

            model.addAttribute(
                    "csvFileName",
                    fileName
            );

            /*
             * templates/03-17-uriage/
             * 05-uriage_touroku_kakunin.html を表示
             */
            return "03-17-uriage/05-uriage_touroku_kakunin";

        } catch (NumberFormatException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    lineNumber
                            + "行目の数量または担当者が整数ではありません。"
            );

        } catch (DateTimeParseException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    lineNumber
                            + "行目の売上日時が正しくありません。"
                            + "例：2026-10-23T10:30:00"
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    e.getMessage()
            );

        } catch (IOException e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "CSVファイルの読み込みに失敗しました。"
            );
        }

        return "redirect:/sales/new";
    }

    /*
     * 05確認画面の「登録実行」で呼び出される。
     * ここで初めてDBへ保存する。
     */
    @PostMapping("/sales/register-csv")
    public String registerCsv(
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        Object sessionData =
                session.getAttribute("confirmSales");

        if (sessionData == null) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "登録する売上データがありません。"
            );

            return "redirect:/sales/new";
        }

        @SuppressWarnings("unchecked")
        List<Sale> confirmSales =
                (List<Sale>) sessionData;

        if (confirmSales.isEmpty()) {

            session.removeAttribute("confirmSales");

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "登録する売上データがありません。"
            );

            return "redirect:/sales/new";
        }

        /*
         * 確認画面を表示している間に同じIDが登録される可能性が
         * あるため、保存直前にも重複をチェックする。
         */
        for (Sale sale : confirmSales) {

            if (saleRepository.existsById(
                    sale.getSaleID()
            )) {

                session.removeAttribute("confirmSales");

                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "売上ID「"
                                + sale.getSaleID()
                                + "」は既に登録されています。"
                );

                return "redirect:/sales/new";
            }
        }

        // ここで初めてDBへ一括登録
        saleRepository.saveAll(confirmSales);

        int registeredCount = confirmSales.size();

        // 同じ内容を二重登録しないよう、一時データを削除
        session.removeAttribute("confirmSales");

        redirectAttributes.addFlashAttribute(
                "message",
                registeredCount
                        + "件の売上情報を登録しました。"
        );

        return "redirect:/sales/register-complete";
    }

    /*
     * 登録完了画面を表示
     */
    @GetMapping("/sales/register-complete")
    public String showCompletePage() {

        return "03-17-uriage/12-uriage_touroku_kanryou";
    }
}