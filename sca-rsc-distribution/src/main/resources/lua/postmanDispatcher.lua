local postman = redis.call('zpopmin', KEYS[1], 1)
if postman[1]
then
    redis.call('zadd', KEYS[1], tonumber(postman[2]+1), postman[1])
    return postman[1]
end
return '-1'     --这里必须是'-1',不能是-1，-1的话spring-data-redis会因为类型转化报错