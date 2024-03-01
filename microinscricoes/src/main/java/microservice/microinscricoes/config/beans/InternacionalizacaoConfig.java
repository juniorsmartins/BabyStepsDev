package microservice.microinscricoes.config.beans;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig implements WebMvcConfigurer {

    // define a localização dos arquivos de mensagens
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("language/messages");

        return messageSource;
    }

    // Define como será gravada a escolha de língua do usuário (pode armazenar em Cookie ou Sessão ou pedir na requisição)
    @Bean
    public LocaleResolver localeResolver() {

        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;

//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.US);
//        return cookieLocaleResolver;

//        SessionLocaleResolver session = new SessionLocaleResolver();
//        session.setDefaultLocale(Locale.US);
//        session.setLocaleAttributeName("session.current.locale");
//        session.setTimeZoneAttributeName("session.current.timezone");
//        return session;
    }

    // registra o localeChangeInterceptor para que ele possa interceptar as requisições
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // responsável por interceptar as requisições e verificar se há alguma mudança de idioma.
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("locale");

        return interceptor;
    }
}

