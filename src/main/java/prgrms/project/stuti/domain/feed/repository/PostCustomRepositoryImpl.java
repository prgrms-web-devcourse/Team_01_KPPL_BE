package prgrms.project.stuti.domain.feed.repository;

import static prgrms.project.stuti.domain.feed.model.QPost.*;
import static prgrms.project.stuti.domain.feed.model.QPostComment.*;
import static prgrms.project.stuti.domain.feed.model.QPostImage.*;
import static prgrms.project.stuti.domain.feed.model.QPostLike.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import prgrms.project.stuti.domain.feed.service.dto.PostResponse;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<PostResponse> findAllWithNoOffset(Long lastPostId, int size, Long memberId) {

		BooleanBuilder dynamicLtId = new BooleanBuilder();

		if (lastPostId != null) {
			dynamicLtId.and(post.id.lt(lastPostId));
		}

		if (memberId != null) {
			dynamicLtId.and(post.member.id.eq(memberId));
		}

		List<Tuple> fetch = jpaQueryFactory
			.select(post.id, post.member.id, post.member.nickName, post.member.mbti, post.member.profileImageUrl,
				post.content, post.updatedAt, postImage.imageUrl)
			.from(post)
			.leftJoin(postImage).on(post.id.eq(postImage.post.id))
			.where(dynamicLtId)
			.orderBy(post.id.desc())
			.limit(size)
			.fetch();

		List<PostResponse> postsDtos = new ArrayList<>();
		for (Tuple tuple : fetch) {
			PostResponse postsDto = PostResponse.builder()
				.postId(tuple.get(post.id))
				.memberId(tuple.get(post.member.id))
				.nickname(tuple.get(post.member.nickName))
				.mbti(tuple.get(post.member.mbti))
				.profileImageUrl(insertEmptyStringIfImageIsNull(tuple.get(post.member.profileImageUrl)))
				.contents(tuple.get(post.content))
				.postImageUrl(insertEmptyStringIfImageIsNull(tuple.get(postImage.imageUrl)))
				.updatedAt(tuple.get(post.updatedAt))
				.likedMembers(findAllLikedMembers(tuple.get(post.id)))
				.totalPostComments(getTotalPostComments(tuple.get(post.id)))
				.build();
			postsDtos.add(postsDto);
		}

		return postsDtos;
	}

	@Override
	public List<Long> findAllLikedMembers(Long postId) {
		return jpaQueryFactory
			.select(postLike.member.id)
			.from(postLike)
			.where(post.id.eq(postId))
			.fetch();
	}

	private Long getTotalPostComments(Long postId) {
		return jpaQueryFactory
			.select(postComment.count())
			.from(postComment)
			.where(postComment.post.id.eq(postId), postComment.parent.isNull())
			.fetchOne();
	}

	private String insertEmptyStringIfImageIsNull(String imageUrl) {
		return imageUrl == null ? "" : imageUrl;
	}
}
