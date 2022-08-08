package prgrms.project.stuti.domain.feed.service.dto;

import java.time.LocalDateTime;

import lombok.Builder;

public record PostCommentChildContents(
	Long parentId,
	Long postCommentId,
	String profileImageUrl,
	Long memberId,
	String nickname,
	String contents,
	LocalDateTime updatedAt
) {
	@Builder
	public PostCommentChildContents {
	}
}
