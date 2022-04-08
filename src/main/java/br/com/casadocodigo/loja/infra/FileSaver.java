package br.com.casadocodigo.loja.infra;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

@Component
public class FileSaver
{

    private static Storage storage = StorageOptions.getDefaultInstance().getService();

    public String write(
        MultipartFile file )
    {
        try {
            final BlobInfo blobInfo = storage.create( BlobInfo.newBuilder( "casadocodigo-williamsartijose",
                file.getOriginalFilename() ).build(),
                file.getBytes(),
                BlobTargetOption.predefinedAcl( PredefinedAcl.PUBLIC_READ ) );
            return blobInfo.getMediaLink();

        } catch( IllegalStateException | IOException e ) {
            throw new RuntimeException( e );
        }
    }

}
