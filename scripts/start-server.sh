#!/bin/bash

```bash
#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop ticket-flow || true
docker rm ticket-flow || true
docker pull 241109717660.dkr.ecr.ap-northeast-2.amazonaws.com/ticket-flow/ticket-flow:latest
docker run -d --name ticket-flow -p 8080:8080 241109717660.dkr.ecr.ap-northeast-2.amazonaws.com/ticket-flow/ticket-flow:latest
echo "--------------- 서버 배포 끝 -----------------"
```