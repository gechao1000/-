https://v3.cn.vuejs.org/

组件的选项 (`data`、`computed`、`methods`、`watch`) 组织逻辑在大多数情况下都有效

```js
import { createApp } from "vue";
import App from "./App.vue"

const app = createApp(App)
app.use(...).mount('#app');
```

响应式变量

```js
import { ref, reactive } from 'vue'

const count = ref(0) //  { value: 0 }
const state = reactive({ count: 0 })
```



`setup`

```
setup() {
	
	return {...}
}

```

`<script setup>`

https://github.com/vuejs/rfcs/blob/script-setup-2/active-rfcs/0000-script-setup.md

```vue
<script setup>
  import { ref } from 'vue'

  const count = ref(0)

  function inc() {
    count.value++
  }
</script>
<template>
  <button @click="inc">{{ count }}</button>
</template>
```

