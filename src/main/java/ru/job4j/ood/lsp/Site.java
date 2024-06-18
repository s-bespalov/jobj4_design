package ru.job4j.ood.lsp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.StringJoiner;

public class Site {

    protected String link;

    public Site(String url) {
        validate(url);
        this.link = url;
    }

    protected void validate(String url) {
        if (!url.startsWith("http://")) {
            throw new IllegalArgumentException("Not http link");
        }
        if (getStatus(url) != 200) {
            throw new IllegalArgumentException("Bad site response status");
        }
    }

    protected int getStatus(String link) {
        var status = 400;
        try {
            var url = new URL(link);
            var con = (HttpURLConnection) url.openConnection();
            status = con.getResponseCode();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public String getURL() {
        return link;
    }

    public void setURL(String url) {
        validate(url);
        this.link = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Site site = (Site) o;
        return Objects.equals(link, site.link);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(link);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Site.class.getSimpleName() + "[", "]")
                .add("link='" + link + "'")
                .toString();
    }
}
