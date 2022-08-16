package prgrms.project.stuti.domain.post.service.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;

public record PostCreateDto(
	Long memberId,
	String contents,
	MultipartFile imageFile
) {

	@Builder
	public PostCreateDto {
	}
}
