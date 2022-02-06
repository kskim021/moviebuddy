package moviebuddy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class JdkLocaleTests {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void printLocales() {
        logging(new Locale("ko","kr"));

        logging(Locale.KOREA);

    }

    void logging(Locale locale) {
        log.info("Locale: {}", locale.toString());
        log.info("Language: {}, DisplayLanguage: {}", locale.getLanguage(), locale.getDisplayLanguage());
        log.info("Country: {}, DisplayCountry: {}", locale.getCountry(), locale.getDisplayCountry());
    }

}
