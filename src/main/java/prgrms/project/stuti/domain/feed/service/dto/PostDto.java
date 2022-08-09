package prgrms.project.stuti.domain.feed.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import prgrms.project.stuti.domain.member.model.Mbti;

public record PostDto(
	Long postId,
	Long memberId,
	String nickname,
	Mbti mbti,
	String profileImageUrl,
	String contents,
	String postImageUrl,
	LocalDateTime updatedAt,
	Long totalPostComments,
	List<Long> likedMembers
) {
	@Builder
	public PostDto {
	}
}
