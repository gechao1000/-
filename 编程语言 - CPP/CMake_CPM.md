https://medium.com/swlh/cpm-an-awesome-dependency-manager-for-c-with-cmake-3c53f4376766

https://github.com/cpm-cmake/CPM.cmake





目录结构

```
.
├── cmake
│   └── CPM.cmake
├── CMakeLists.txt
└── src
    └── main.cpp
```

CMakeLists.txt

```
cmake_minimum_required(VERSION 3.18)
project(cpm_example)
set(CMAKE_CXX_STANDARD 17)

add_executable(cpm_example src/main.cpp)

include(cmake/CPM.cmake)
CPMAddPackage(
    NAME spdlog
    GITHUB_REPOSITORY gabime/spdlog
    VERSION 1.7.0)

target_link_libraries(cpm_example spdlog)
```

main.cpp

```
#include <iostream>
#include <spdlog/spdlog.h>

int main()
{
    std::cout << "Hello world" << std::endl;
    spdlog::info("Hello World!");
    return 0;
}

```



编译

```
mkdir build && cd build

cmake .. && make -j
```





Arch 安装 spdlog、fmt

> https://github.com/gabime/spdlog

```
cmake_minimum_required(VERSION 3.22)
project(hello)
set(CMAKE_CXX_STANDARD 17)

add_definitions(-DSPDLOG_FMT_EXTERNAL)
add_executable(hello main.cpp)
target_link_libraries(hello fmt spdlog)
```

