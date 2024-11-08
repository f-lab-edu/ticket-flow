package github.ticketflow.config.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object lock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockKey = distributedLock.key();
        RLock lock = redissonClient.getLock(lockKey);

        boolean isLocked = false;

        try {
            isLocked = lock.tryLock(5, 20, TimeUnit.SECONDS);
            if (isLocked) {
                return joinPoint.proceed();
            } else {
                throw new IllegalStateException("Could not acquire lock for key: " + lockKey);
            }
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
