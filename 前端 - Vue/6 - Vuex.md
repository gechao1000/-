###### 介绍

> https://vuex.vuejs.org/

```
yarn add vuex

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
```

###### 声明

```
const store = new Vuex.Store({
  state: {
  	count: 0	// $store.state.count
  },	
  mutations: {
  	increment (state) {	//$store.commit('increment')
      state.count++
    }
  },
  actions: {},
  modules: {}
});

new Vue({
  store
}).$mount('#app')
```

###### mapState

```
import { mapState } from 'vuex';

computed: mapState(["count"])

// 访问
{{ count }}
```

###### mapMutations

```
import { mapMutations } from 'vuex';

methods: mapMutations(['increment']),

// 访问
@click="increment"
```

###### mapGetters

```
// store属性
getters: {
    now() {
        return new Date().toISOString()
    }
}

// 调用
import { mapState, mapGetters } from 'vuex';

computed: {
    ...mapState(['count']),
    ...mapGetters(['now'])
},
```

###### mapActions

```
// store属性
actions: {
    asyncAdd({commit}) {
        commit('increment')
    }
},

// 调用
import { mapActions } from 'vuex';

methods:mapActions(['asyncAdd']),
```

