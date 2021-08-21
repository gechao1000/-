Simple hello world

```
---CMakeLists.txt
cmake_minimum_required(VERSION 3.10)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

project(hello VERSION 1.0)
add_executable(hello main.cpp)


---GCC过程
g++ -o hello main.cpp
```

Running CMake

```
cmake . && make && ./hello

----方式2
mkdir build && cd build
cmake ..
make
./hello
```

Adding a header file

```
----- CMakeLists.txt
target_include_directories(hello PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/include)

----- include/Blah.h 
#pragma once
#include <iostream>

class Blah {
public:
    inline void boo() {
        std::cout << "Boo!" << std::endl;
    }
};

----- main.cpp
#include "Blah.h"

Blah().boo();

```

Multiple source files

```
----- CMakeLists.txt
file(GLOB_RECURSE SRC_FILES src/*.cpp)
add_executable(hello main.cpp ${SRC_FILES})

------ main.cpp  src/Blah.cpp
```

My own lib

```
* Create a lib from some source files
* Replace add_executable with add_library

* Can then include it in your main executable


----- blah/CMakeLists.txt
add_library(blah STATIC Blah.cpp)
target_include_directories(blah PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/include)

------ CMakeLists.txt
add_subdirectory(blah)

add_executable(hello main.cpp)
target_link_libraries(hello PUBLIC blah)
```

Depending on external library with find_package

```
yay -S sfml

find_package(SFML 2 REQUIRED network audio graphics window system)

target_include_directories(hello PUBLIC ${SFML_INCLUDE_DIR})
target_link_libraries(hello PUBLIC ${SFML_LIBRARIES} ${SFML_DEPENDENCIES})
```

Depending on external lib manually

```
---- system lib directory
pacman -S zlib
/usr/include/zlib.h
/usr/lib/libz.*

find_library(ZLIB libz.a REQUIRED PATHS /usr/lib/libz.a)
target_link_libraries(hello PUBLIC ${ZLIB})


----- custom path  /tmp/aaa/mylib.so
find_library(MYLIB mylib PATHS /tmp/aaa)
target_link_libraries(hello PUBLIC ${MYLIB})

* include directory
set(MYLIB_INCLUDE_DIRS /tmp/aaa/include)
target_include_directories(hello PUBLIC ${MYLIB_INCLUDE_DIRS})

```

