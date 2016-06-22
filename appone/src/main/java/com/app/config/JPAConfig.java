package com.app.config;
import com.app.config.appSetup.model.AppConfiguration;
import com.app.config.appSetup.model.JpaProperties;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.PersistenceProvider;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("dataSource") BasicDataSource dataSource, @Qualifier("appConfig") AppConfiguration appConfig) throws ClassNotFoundException, PropertyVetoException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaDialect(new EclipseLinkJpaDialect());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPersistenceUnitName(getPackage());
        entityManagerFactoryBean.setPackagesToScan(new String[] { getPackage() + ".app", "com.athena", "com.spartan" });
        entityManagerFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        entityManagerFactoryBean.setValidationMode(ValidationMode.NONE);
        /* Jpa Properties from appConfiguration.xml */
        Properties jpaProterties = new Properties();
        for (JpaProperties jpaProperty : appConfig.getJpaProperties()) {
            jpaProterties.put(jpaProperty.getName(), jpaProperty.getValue());
        }
        entityManagerFactoryBean.setJpaProperties(jpaProterties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PersistenceProvider provider() {
        PersistenceProvider provider = new org.eclipse.persistence.jpa.PersistenceProvider();
        return provider;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) throws ClassNotFoundException, PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        System.out.println("FROM TRANSACTION MANAGER ENTITY MANAGER FACTORY BEAN  is " + entityManagerFactoryBean.getObject());
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    private String getPackage() {
        String pkgName = "com";
        return pkgName;
    }
}
