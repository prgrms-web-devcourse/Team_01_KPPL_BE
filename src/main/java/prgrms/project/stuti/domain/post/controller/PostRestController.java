package prgrms.project.stuti.domain.post.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import prgrms.project.stuti.domain.post.controller.dto.PostRequest;
import prgrms.project.stuti.domain.post.service.PostService;
import prgrms.project.stuti.domain.post.service.dto.PostChangeDto;
import prgrms.project.stuti.domain.post.service.dto.PostCreateDto;
import prgrms.project.stuti.domain.post.service.response.PostsResponse;
import prgrms.project.stuti.domain.post.service.response.PostDetailResponse;

@RestController
@RequiredArgsConstructor
public class PostRestController {

	private final PostService postService;

	@PostMapping("/api/v1/posts")
	public ResponseEntity<PostDetailResponse> registerPost(
		@Valid @ModelAttribute PostRequest registerPostRequest, @AuthenticationPrincipal Long memberId
	) {
		PostCreateDto postCreateDto = PostMapper.toPostCreateDto(registerPostRequest, memberId);
		PostDetailResponse postDetailResponse = postService.registerPost(postCreateDto);

		return ResponseEntity.ok(postDetailResponse);
	}

	@GetMapping("/api/v1/posts")
	public ResponseEntity<PostsResponse> getAllPosts(
		@RequestParam(value = "lastPostId", required = false) Long lastPostId,
		@RequestParam(defaultValue = "10") int size
	) {
		PostsResponse postsResponse = postService.getAllPosts(lastPostId, size);

		return ResponseEntity.ok(postsResponse);
	}

	@PostMapping("/api/v1/posts/{postId}")
	public ResponseEntity<PostDetailResponse> changePost(
		@PathVariable Long postId, @Valid @ModelAttribute PostRequest registerPostRequest
	) {
		PostChangeDto postChangeDto = PostMapper.toPostChangeDto(registerPostRequest, postId);
		PostDetailResponse postDetailResponse = postService.changePost(postChangeDto);

		return ResponseEntity.ok(postDetailResponse);
	}

	@DeleteMapping("/api/v1/posts/{postId}")
	public ResponseEntity<Void> deletePost(
		@PathVariable Long postId
	) {
		postService.deletePost(postId);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}

	@GetMapping("/api/v1/posts/members/{memberId}")
	public ResponseEntity<PostsResponse> getMemberPosts(
		@PathVariable Long memberId, @RequestParam(value = "lastPostId", required = false) Long lastPostId,
		@RequestParam(defaultValue = "10") int size
	) {
		PostsResponse postsResponse = postService.getMemberPosts(memberId, lastPostId, size);

		return ResponseEntity.ok(postsResponse);
	}
}
