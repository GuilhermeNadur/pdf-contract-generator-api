package com.pdfcontractgeneratorapi.util.paragraph;

import com.itextpdf.text.Paragraph;
import com.pdfcontractgeneratorapi.domain.Paragrafo;

public class ParagraphConverter {

    private ParagraphConverter() {}

    public static Paragraph convertToITextParagraph(Paragrafo paragrafo) {
        return ParagraphAdapter.builder()
                .text(paragrafo.getText())
                .font(paragrafo.getFont())
                .fontSize(paragrafo.getFontSize())
                .alignment(paragrafo.getAlignment())
                .build();
    }
}
