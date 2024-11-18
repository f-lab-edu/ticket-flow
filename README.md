# Ticket-Flow
### 문화생활을 즐길 수 있는 스포츠, 연극, 뮤지컬 등 관람 티켓을 예매할 수 있는 프로젝트입니다.

### 🌟 왜 이 프로젝트를 선택했을까요?
- 대용량 트래픽을 어떻게 관리할 수 있는지 알아보고 싶었습니다.
- 테스트 자동화 경험을 통한 품질 향상을 시키고 싶었습니다.
- 지속적인 통합과 배포(CI/CD)를 통한 협업 효율성 증대시키고 싶었습니다.
- 만약, 많은 사람들이 데이터에 동시에 접근을 하려고 할 때, 동시성 제어를 알아보고 싶었습니다.

### 🌟 이런 부분을 고려하면서 프로젝트를 만들었어요!
- 유지보수를 위해 객체지향을 이해하고 이를 코드에 적용하려고 했습니다.
- 냄새나는 코드가 아닌 읽기 좋은 코드를 작성하려고 했습니다.
- docker를 통해 일괄된 개발 환경을 제공하고자 했습니다.

### 🌟 프로젝트 진행 중 이슈와 해결 과정
#### CI/CD 구축을 위해 Github Actions로 CI/CD 구축하는 과정
- [프로젝트 개발 효율성을 위한 CI/CD 적용 과정](https://velog.io/@sang_hyeok_2/Project-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B0%9C%EB%B0%9C-%ED%9A%A8%EC%9C%A8%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-CICD-%EC%A0%81%EC%9A%A9-%EA%B3%BC%EC%A0%95)

#### 대용량 트랙픽을 감당하기 위한 서버 확장 방법과 세션 불일치 문제 해결
- [대용량 트래픽이 오면 서버를 어떻게 확장을 하는게 좋을까?](https://velog.io/@sang_hyeok_2/%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD%EC%9D%B4-%EC%98%A4%EB%A9%B4-%EC%84%9C%EB%B2%84%EB%A5%BC-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%99%95%EC%9E%A5%EC%9D%84-%ED%95%98%EB%8A%94%EA%B2%8C-%EC%A2%8B%EC%9D%84%EA%B9%8C)
- [서버 분산 처리 과정에서 발생하는 데이터 Session 불일치 문제를 어떻게 해결할까?](https://velog.io/@sang_hyeok_2/%EC%84%9C%EB%B2%84-%EB%B6%84%EC%82%B0-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95%EC%97%90%EC%84%9C-%EB%B0%9C%EC%83%9D%ED%95%98%EB%8A%94-%EB%8D%B0%EC%9D%B4%ED%84%B0-Session-%EB%B6%88%EC%9D%BC%EC%B9%98-%EB%AC%B8%EC%A0%9C%EB%A5%BC-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4%EA%B2%B0%ED%95%A0%EA%B9%8C)
- [In-Memory-Database와 JWT 토큰](https://velog.io/@sang_hyeok_2/Project-In-Memory-Database%EC%99%80-JWT-%ED%86%A0%ED%81%B0-%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD-3)

#### 동시성 문제 발생 시 해결 방법
- [인기 검색어 동시성 문제 해결하기](https://velog.io/@sang_hyeok_2/Project-%EC%9D%B8%EA%B8%B0-%EA%B2%80%EC%83%89%EC%96%B4-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0)
- [Redis로 동시서 제어하기 - SETNX](https://velog.io/@sang_hyeok_2/Project-Redis%EB%A1%9C-%EB%8F%99%EC%8B%9C%EC%84%9C-%EC%A0%9C%EC%96%B4%ED%95%98%EA%B8%B0-SETNX)
- [Redis로 동시성 제어하기 - Spin Lock](https://velog.io/@sang_hyeok_2/Project-Redis%EB%A1%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%A0%9C%EC%96%B4%ED%95%98%EA%B8%B0-Spin-Lock)
- [Redisson을 이용한 좌석 선택 동시성 제어하기](https://velog.io/@sang_hyeok_2/Project-Redisson%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%A2%8C%EC%84%9D-%EC%84%A0%ED%83%9D-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%A0%9C%EC%96%B4%ED%95%98%EA%B8%B0)

#### 경기 조회 api 속도 개선
- [redis로 성능 개선 하기](https://velog.io/@sang_hyeok_2/Project-redis%EB%A1%9C-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0-%ED%95%98%EA%B8%B0)

#### 가독성과 코드 개선 과정
- [프로젝트에서 Test Fixture를 어떻게 사용할 지 고민해보기](https://velog.io/@sang_hyeok_2/Project-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90%EC%84%9C-Test-Fixture%EB%A5%BC-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%A7%80-%EA%B3%A0%EB%AF%BC%ED%95%B4%EB%B3%B4%EA%B8%B0)

### 🌟 프로젝트의 서버 구조도
![스크린샷 2024-09-25 오후 5 15 54](https://github.com/user-attachments/assets/9e7491cd-957e-4cf9-994e-6f7c3e293be2)

### 🌟 프로젝트의 ERD
https://www.erdcloud.com/d/B2ESKfPBPiPHyu4fw
<img width="1055" alt="스크린샷 2024-11-15 오후 8 25 47" src="https://github.com/user-attachments/assets/eb0703e3-f1c0-4471-8749-887aebfa8ee9">

### 🌟 프로젝트의 사용한 기술 스택
![Section 1](https://github.com/user-attachments/assets/78866dfd-d050-4624-96b8-1fe6c068f2ab)
