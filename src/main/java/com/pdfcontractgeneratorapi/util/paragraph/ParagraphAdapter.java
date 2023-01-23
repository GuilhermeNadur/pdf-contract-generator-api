package com.pdfcontractgeneratorapi.util.paragraph;

import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class ParagraphAdapter {

    private ParagraphAdapter() {}

    public static ParagraphAdapterBuilder builder() {
        return new ParagraphAdapterBuilder();
    }

    public static class ParagraphAdapterBuilder {

        private String text;
        private String font;
        private int fontSize;
        private int alignment = Element.ALIGN_UNDEFINED;

        ParagraphAdapterBuilder() {}

        public ParagraphAdapterBuilder text(final String text) {
            this.text = text;
            return this;
        }

        public ParagraphAdapterBuilder font(final String font) {
            this.font = font;
            return this;
        }

        public ParagraphAdapterBuilder fontSize(final int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public ParagraphAdapterBuilder alignment(final int alignment) {
            this.alignment = alignment;
            return this;
        }

        public Paragraph build() {
            var paragraph = new Paragraph(this.text, FontFactory.getFont(this.font, this.fontSize));
            paragraph.setAlignment(this.alignment);
            return paragraph;
        }
    }
}
