package pe.msbaek.studyingtest.builder;

import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * https://medium.com/naver-cloud-platform/%EA%B8%B0%EC%88%A0-%EC%BB%A8%ED%85%90%EC%B8%A0-%EB%AC%B8%EC%9E%90-%EC%95%8C%EB%A6%BC-%EB%B0%9C%EC%86%A1-%EC%84%9C%EB%B9%84%EC%8A%A4-sens%EC%9D%98-mapstruct-%EC%A0%81%EC%9A%A9%EA%B8%B0-8fd2bc2bc33b
 */
@Mapper
public interface PersonMapper {
//    PersonMapper INSTANCE = getMapper(PersonMapper.class);
    PersonDto toDto(Person person);
    Person fromDto(PersonDto dto);
}