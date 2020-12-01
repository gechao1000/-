[https://github.com/gogs/gogs](https://github.com/gogs/gogs)

```plain
docker pull gogs/gogs
mkdir -p /var/gogs
docker run --name=gogs -p 10022:22 -p 3000:3000 -v /var/gogs:/data -d gogs/gogs
```

