标准模板库

```
#include <vector>

// 容器
vector<int> v;
v.push_back(10);
v.push_back(10);

// 迭代器
vector<int>::iterator it = v.begin(); // 第一个元素
vector<int>::iterator itEnd = v.begin(); // 最后一个元素的下一个位置

// 遍历
while (it != itEnd)
{
    cout << *it << endl;
    it++;
}

// 简单写法
for (vector<int>::iterator it = v.begin(); it != v.end(); it++)
{
    cout << *it << endl;
}
```

```
#include <algorithm>

// 回调函数
void print(int val)
{
    cout << val << endl;
}


for_each(v.begin(), v.end(), ::print);
```

