//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.orm.hibernate5;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;

public class LocalSessionFactoryBean extends HibernateExceptionTranslator implements FactoryBean<SessionFactory>, ResourceLoaderAware, InitializingBean, DisposableBean {
    private DataSource dataSource;
    private Resource[] configLocations;
    private String[] mappingResources;
    private Resource[] mappingLocations;
    private Resource[] cacheableMappingLocations;
    private Resource[] mappingJarLocations;
    private Resource[] mappingDirectoryLocations;
    private Interceptor entityInterceptor;
    private ImplicitNamingStrategy implicitNamingStrategy;
    private PhysicalNamingStrategy physicalNamingStrategy;
    private Object jtaTransactionManager;
    private MultiTenantConnectionProvider multiTenantConnectionProvider;
    private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;
    private TypeFilter[] entityTypeFilters;
    private Properties hibernateProperties;
    private Class<?>[] annotatedClasses;
    private String[] annotatedPackages;
    private String[] packagesToScan;
    private AsyncTaskExecutor bootstrapExecutor;
    private boolean metadataSourcesAccessed = false;
    private MetadataSources metadataSources;
    private ResourcePatternResolver resourcePatternResolver;
    private Configuration configuration;
    private SessionFactory sessionFactory;

    public LocalSessionFactoryBean() {
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocations = new Resource[]{configLocation};
    }

    public void setConfigLocations(Resource... configLocations) {
        this.configLocations = configLocations;
    }

    public void setMappingResources(String... mappingResources) {
        this.mappingResources = mappingResources;
    }

    public void setMappingLocations(Resource... mappingLocations) {
        this.mappingLocations = mappingLocations;
    }

    public void setCacheableMappingLocations(Resource... cacheableMappingLocations) {
        this.cacheableMappingLocations = cacheableMappingLocations;
    }

    public void setMappingJarLocations(Resource... mappingJarLocations) {
        this.mappingJarLocations = mappingJarLocations;
    }

    public void setMappingDirectoryLocations(Resource... mappingDirectoryLocations) {
        this.mappingDirectoryLocations = mappingDirectoryLocations;
    }

    public void setEntityInterceptor(Interceptor entityInterceptor) {
        this.entityInterceptor = entityInterceptor;
    }

    public void setImplicitNamingStrategy(ImplicitNamingStrategy implicitNamingStrategy) {
        this.implicitNamingStrategy = implicitNamingStrategy;
    }

    public void setPhysicalNamingStrategy(PhysicalNamingStrategy physicalNamingStrategy) {
        this.physicalNamingStrategy = physicalNamingStrategy;
    }

    public void setJtaTransactionManager(Object jtaTransactionManager) {
        this.jtaTransactionManager = jtaTransactionManager;
    }

    public void setMultiTenantConnectionProvider(MultiTenantConnectionProvider multiTenantConnectionProvider) {
        this.multiTenantConnectionProvider = multiTenantConnectionProvider;
    }

    public void setCurrentTenantIdentifierResolver(CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        this.currentTenantIdentifierResolver = currentTenantIdentifierResolver;
    }

    public void setEntityTypeFilters(TypeFilter... entityTypeFilters) {
        this.entityTypeFilters = entityTypeFilters;
    }

    public void setHibernateProperties(Properties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    public Properties getHibernateProperties() {
        if (this.hibernateProperties == null) {
            this.hibernateProperties = new Properties();
        }

        return this.hibernateProperties;
    }

    public void setAnnotatedClasses(Class<?>... annotatedClasses) {
        this.annotatedClasses = annotatedClasses;
    }

    public void setAnnotatedPackages(String... annotatedPackages) {
        this.annotatedPackages = annotatedPackages;
    }

    public void setPackagesToScan(String... packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    public void setBootstrapExecutor(AsyncTaskExecutor bootstrapExecutor) {
        this.bootstrapExecutor = bootstrapExecutor;
    }

    public void setMetadataSources(MetadataSources metadataSources) {
        Assert.notNull(metadataSources, "MetadataSources must not be null");
        this.metadataSourcesAccessed = true;
        this.metadataSources = metadataSources;
    }

    public MetadataSources getMetadataSources() {
        this.metadataSourcesAccessed = true;
        if (this.metadataSources == null) {
            BootstrapServiceRegistryBuilder builder = new BootstrapServiceRegistryBuilder();
            if (this.resourcePatternResolver != null) {
                builder = builder.applyClassLoader(this.resourcePatternResolver.getClassLoader());
            }

            this.metadataSources = new MetadataSources(builder.build());
        }

        return this.metadataSources;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }

    public ResourceLoader getResourceLoader() {
        if (this.resourcePatternResolver == null) {
            this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
        }

        return this.resourcePatternResolver;
    }

    public void afterPropertiesSet() throws IOException {
        if (this.metadataSources != null && !this.metadataSourcesAccessed) {
            this.metadataSources = null;
        }

        LocalSessionFactoryBuilder sfb = new LocalSessionFactoryBuilder(this.dataSource, this.getResourceLoader(), this.getMetadataSources());
        Resource[] var2;
        int var3;
        int var4;
        Resource resource;
        if (this.configLocations != null) {
            var2 = this.configLocations;
            var3 = var2.length;

            for(var4 = 0; var4 < var3; ++var4) {
                resource = var2[var4];
                sfb.configure(resource.getURL());
            }
        }

        if (this.mappingResources != null) {
            String[] var7 = this.mappingResources;
            var3 = var7.length;

            for(var4 = 0; var4 < var3; ++var4) {
                String mapping = var7[var4];
                Resource mr = new ClassPathResource(mapping.trim(), this.resourcePatternResolver.getClassLoader());
                sfb.addInputStream(mr.getInputStream());
            }
        }

        if (this.mappingLocations != null) {
            var2 = this.mappingLocations;
            var3 = var2.length;

            for(var4 = 0; var4 < var3; ++var4) {
                resource = var2[var4];
                sfb.addInputStream(resource.getInputStream());
            }
        }

        if (this.cacheableMappingLocations != null) {
            var2 = this.cacheableMappingLocations;
            var3 = var2.length;

            for(var4 = 0; var4 < var3; ++var4) {
                resource = var2[var4];
                sfb.addCacheableFile(resource.getFile());
            }
        }

        if (this.mappingJarLocations != null) {
            var2 = this.mappingJarLocations;
            var3 = var2.length;

            for(var4 = 0; var4 < var3; ++var4) {
                resource = var2[var4];
                sfb.addJar(resource.getFile());
            }
        }

        if (this.mappingDirectoryLocations != null) {
            var2 = this.mappingDirectoryLocations;
            var3 = var2.length;

            for(var4 = 0; var4 < var3; ++var4) {
                resource = var2[var4];
                File file = resource.getFile();
                if (!file.isDirectory()) {
                    throw new IllegalArgumentException("Mapping directory location [" + resource + "] does not denote a directory");
                }

                sfb.addDirectory(file);
            }
        }

        if (this.entityInterceptor != null) {
            sfb.setInterceptor(this.entityInterceptor);
        }

        if (this.implicitNamingStrategy != null) {
            sfb.setImplicitNamingStrategy(this.implicitNamingStrategy);
        }

        if (this.physicalNamingStrategy != null) {
            sfb.setPhysicalNamingStrategy(this.physicalNamingStrategy);
        }

        if (this.jtaTransactionManager != null) {
            sfb.setJtaTransactionManager(this.jtaTransactionManager);
        }

        if (this.multiTenantConnectionProvider != null) {
            sfb.setMultiTenantConnectionProvider(this.multiTenantConnectionProvider);
        }

        if (this.currentTenantIdentifierResolver != null) {
            sfb.setCurrentTenantIdentifierResolver(this.currentTenantIdentifierResolver);
        }

        if (this.entityTypeFilters != null) {
            sfb.setEntityTypeFilters(this.entityTypeFilters);
        }

        if (this.hibernateProperties != null) {
            sfb.addProperties(this.hibernateProperties);
        }

        if (this.annotatedClasses != null) {
            sfb.addAnnotatedClasses(this.annotatedClasses);
        }

        if (this.annotatedPackages != null) {
            sfb.addPackages(this.annotatedPackages);
        }

        if (this.packagesToScan != null) {
            sfb.scanPackages(this.packagesToScan);
        }

        this.configuration = sfb;
        this.sessionFactory = this.buildSessionFactory(sfb);
    }

    protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
        return this.bootstrapExecutor != null ? sfb.buildSessionFactory(this.bootstrapExecutor) : sfb.buildSessionFactory();
    }

    public final Configuration getConfiguration() {
        if (this.configuration == null) {
            throw new IllegalStateException("Configuration not initialized yet");
        } else {
            return this.configuration;
        }
    }

    public SessionFactory getObject() {
        return this.sessionFactory;
    }

    public Class<?> getObjectType() {
        return this.sessionFactory != null ? this.sessionFactory.getClass() : SessionFactory.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() {
        this.sessionFactory.close();
    }
}
