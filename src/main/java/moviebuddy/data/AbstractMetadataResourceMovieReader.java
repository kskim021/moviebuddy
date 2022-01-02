package moviebuddy.data;

import moviebuddy.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.net.URL;

public class AbstractMetadataResourceMovieReader implements ResourceLoaderAware {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private String metadata;

    private ResourceLoader resourceLoader;

    public String getMetadata() {
        return metadata;
    }

    @Value("${movie.metadata}")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getMetaResource(){
        return resourceLoader.getResource(getMetadata());
    }

    private URL getMetadataUrl() {
        String location = getMetadata();
        if (location.startsWith("file:")) {

        } else if (location.startsWith("http:")) {

        } else {

        }
        return ClassLoader.getSystemResource(location);

    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        //URL metadataUrl = ClassLoader.getSystemResource(metadata);

        Resource resource = getMetaResource();
        if(!resource.exists()) {
            throw new FileNotFoundException(metadata);
        }

        if(!resource.isReadable()) {
            throw new ApplicationException(String.format("cannot read to metadata. [$s]", metadata));
        }

        log.info(resource + " is ready.");

        /*URL metadataUrl = getMetadataUrl();

        if (Objects.isNull(metadataUrl)) {
            throw new FileNotFoundException(metadata);
        }*/

        /*if (Files.isReadable(Path.of(metadataUrl.toURI())) == false) {
            throw new ApplicationException(String.format("cannot read to metadata. [$s]", metadata));
        }*/
    }


    @PreDestroy
    public void destroy() throws Exception {
        log.info("Destroyed bean");
    }

}
