package prgrms.project.stuti.domain.feed.service;

import java.util.List;

import prgrms.project.stuti.domain.feed.model.Comment;
import prgrms.project.stuti.domain.feed.model.Feed;
import prgrms.project.stuti.domain.feed.service.dto.CommentChildContents;
import prgrms.project.stuti.domain.feed.service.dto.CommentContentsResponse;
import prgrms.project.stuti.domain.feed.service.dto.CommentGetDto;
import prgrms.project.stuti.domain.feed.service.dto.CommentParentContents;
import prgrms.project.stuti.domain.feed.service.dto.CommentResponse;
import prgrms.project.stuti.global.page.offset.PageResponse;

public class CommentConverter {

	public static Comment toComment(String contents, Feed feed, Comment parentComment) {
		return new Comment(contents, parentComment, feed.getMember(), feed);
	}

	public static CommentResponse toCommentResponse(Comment savedComment) {
		Long parentId = null;
		if (savedComment.getParent() != null) {
			parentId = savedComment.getParent().getId();
		}
		return CommentResponse.builder()
			.postCommentId(savedComment.getId())
			.parentId(parentId)
			.profileImageUrl(savedComment.getMember().getProfileImageUrl())
			.memberId(savedComment.getMember().getId())
			.nickname(savedComment.getMember().getNickName())
			.contents(savedComment.getContent())
			.updatedAt(savedComment.getUpdatedAt())
			.build();
	}

	public static CommentGetDto toCommentGetDto(Long postId, Long lastPostId, int size) {
		return new CommentGetDto(postId, lastPostId, size);
	}

	public static PageResponse<CommentParentContents> toCommentResponse(List<Comment> comments, boolean hasNext,
		Long totalParentComments) {
		List<CommentParentContents> contents = createContents(comments);

		return new PageResponse<>(contents, hasNext, totalParentComments);
	}

	private static List<CommentParentContents> createContents(List<Comment> comments) {
		return comments.stream().map(
			comment -> CommentParentContents.builder()
				.commentId(comment.getId())
				.parentId(null)
				.profileImageUrl(comment.getMember().getProfileImageUrl())
				.memberId(comment.getMember().getId())
				.nickname(comment.getMember().getNickName())
				.contents(comment.getContent())
				.createdAt(comment.getCreatedAt())
				.children(comment.getChildren().stream().map(
					childComment -> CommentChildContents.builder()
						.parentId(childComment.getParent().getId())
						.commentId(childComment.getId())
						.profileImageUrl(childComment.getMember().getProfileImageUrl())
						.memberId(childComment.getMember().getId())
						.nickname(childComment.getMember().getNickName())
						.contents(childComment.getContent())
						.createdAt(childComment.getCreatedAt())
						.build()
				).toList()).build()
		).toList();
	}

	public static CommentContentsResponse toCommentContentsResponse(Comment comment) {
		Long parentId = null;
		if(comment.getParent() != null) {
			parentId = comment.getParent().getId();
		}
		return new CommentContentsResponse(comment.getId(), parentId, comment.getContent());
	}
}
