package ru.job4j.ood.lsp;

public class SecureSite extends Site {

    public SecureSite(String url) {
        super(url);
    }

    @Override
    protected void validate(String url) {
        if (!url.startsWith("https://")) {
            throw new IllegalArgumentException("Secure site requires https!");
        }
    }

    @Override
    public void setURL(String url) {
        super.setURL(url);
    }

    @Override
    public void newVisitor() {
        visitors++;
    }
}
