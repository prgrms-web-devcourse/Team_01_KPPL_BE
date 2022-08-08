package prgrms.project.stuti.domain.feed.repository;

import prgrms.project.stuti.domain.feed.service.dto.CommentParentContents;
import prgrms.project.stuti.global.page.offset.PageResponse;

public interface PostCommentCustomRepository {

	PageResponse<CommentParentContents> findAllByPostIdAndParentIdIsNUllWithNoOffset(Long postId, Long lastCommentId,
		int size);
}
