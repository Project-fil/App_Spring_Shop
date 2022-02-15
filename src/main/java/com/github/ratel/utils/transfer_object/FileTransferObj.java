package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.FileEntity;
import com.github.ratel.payload.response.FileEntityResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileTransferObj {

    public static FileEntityResponse fromFile(FileEntity fileEntity) {
        return new FileEntityResponse(

        );
    }

}
