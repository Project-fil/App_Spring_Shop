package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.FileEntity;
import com.github.ratel.payload.response.FileEntityResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class FileTransferObj {

    public static FileEntityResponse fromFile(FileEntity fileEntity) {
        if (Objects.isNull(fileEntity)) {
            return null;
        }
        return new FileEntityResponse(
                fileEntity.getId(),
                fileEntity.getPath(),
                fileEntity.getFileName(),
                fileEntity.getContentType(),
                fileEntity.getSize(),
                fileEntity.isRemoved(),
                fileEntity.getCratedAt(),
                fileEntity.getUpdatedAt()
        );
    }

}
