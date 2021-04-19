###### Router

> https://router.vuejs.org/

```
npm install vue-router

import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
```

###### 声明

```
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

const routes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar }
]

const router = new VueRouter({
  routes 
})

new Vue({
  router
}).$mount('#app')
```

###### 使用

```
<div>
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <router-link to="/foo">Go to Foo</router-link>
    <router-link to="/bar">Go to Bar</router-link>
</div>

<!-- 路由出口 -->
<!-- 路由匹配到的组件将渲染在这里 -->
<router-view></router-view>
```


###### 多个路由

```
<router-view ></router-view>
<router-view name="left"></router-view>
<router-view name="right"></router-view>

{
    path: '/',
    components: {
        default:Hello,
        left:Hi1,
        right:Hi2
    }
},
```

###### 传递参数

```
routes: [
    {
      path: '/',
      name: 'Hello',	// $route.name
      component: Hello
    }
]

// $route.params.username
<router-link :to="{name:xxx,params:{key:value}}">valueString</router-link>
```


###### url 传参

```
{
    path:'/params/:newsId/:newsTitle',
    component:Params
}

<router-link to="/params/198/good">params</router-link>

$route.params.newsId
$route.params.newsTitle
```

###### 重定向，别名，404

```
{ path: '/a', redirect: '/b' }
{ path: '/a', component: A, alias: '/b' }

// 最后一项
{ path: '*', component: ErrorPage }
```

###### 生命周期

```
const router = new VueRouter({ ... })

router.beforeEach((to, from, next) => {
  // ...
  next();
})

beforeRouteEnter
beforeRouteLeave
```

###### 编程式导航

```
router.push('home')
router.push({ path: 'home' })
router.push({ name: 'user', params: { userId: '123' }})
```

