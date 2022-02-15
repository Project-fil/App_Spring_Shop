package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Comment;
import com.github.ratel.payload.response.CommentResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentsTransferObj {

    public static CommentResponse fromComment(Comment payload) {
        return new CommentResponse(

        );
    }

}
