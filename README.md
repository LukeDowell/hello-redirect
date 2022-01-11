# Spring Transparent Proxy Example

Web will attempt to communicate to backend but will hit Proxy without knowing about it. Proxy
should be able to either completely forward the request or intercept and do it's own work while
making sure Backend stays up to date.

## Relevant Reading

https://docs.spring.io/spring-framework/docs/5.3.12/reference/html/web.html#filters-forwarded-headers

https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#howto.webserver.use-behind-a-proxy-server

