<script setup lang="ts">
import { apiGetAllOrders } from './OdManagementAPIs';
import { ref, onMounted, watchEffect } from 'vue';
import type {orderData} from './OdDataTypes'

const tableData = ref([]);
// const siderWidth = ref<string>('40%');

onMounted(() => {
    apiGetAllOrders
        .then(res => {
            console.log(res.data.data);
            tableData.value = res.data.data;
        })
        .catch(err => {
            console.error(err);
        });
});

const siderCollapsed = ref<boolean>(true);

const handleCloseSider = () => {
    console.log('lalalalal');
    siderCollapsed.value = true;
};

const currOdData = ref<orderData|undefined>();

const handleOrderDetailSider = (orderDetail:orderData) => {
    currOdData.value = orderDetail;
    console.log(currOdData.value);
    siderCollapsed.value = false
}
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <!-- :content-style="{display:'flex',flexFlow:'column nowrap'}" -->
            <n-layout embedded class="contents-style" :content-style="{ display: 'flex', flexFlow: 'column nowrap' }">
                <n-layout-header :style="{ backgroundColor: 'transparent' }">
                    <h2>Orders Management</h2>
                    <!-- <n-space>
                        <n-button type="info">Add</n-button>
                        <n-button type="error">{{ `Delete` }}</n-button>
                    </n-space> -->
                </n-layout-header>
                <n-layout-content :style="{ backgroundColor: 'transparent', flex: 1 }">
                    <!-- <ProdDataTable :tableData="tableData" @delete-data-row="handleDelete" v-model:checked-rows="checkedRowKeysRef" /> -->
                    <OdDataTable :tableData="tableData" @display-order-detail="handleOrderDetailSider"/>
                </n-layout-content>
            </n-layout>

            <!-- class="category-sider" -->
            <!-- :content-style="{ height:'100%' }" -->
                <!-- show-trigger="bar" -->
            <n-layout-sider
                :collapsed="siderCollapsed"
                :style="{ backgroundColor: 'transparent' }"
                :width="420"
                collapse-mode="transform"
                :collapsed-width="0"
            >
                <!-- :show-collapsed-content="false" -->
                <!-- v-model:category-data="categories"
                    @get-prods-by-category="handleGetProdsByCategory"
                    @get-all-prods="handleGetAllProds" -->
                <!-- <ProdCategoriesSider /> -->
                <OdSider @close-sider="handleCloseSider" :OrderDetail="currOdData"/>
            </n-layout-sider>
        </n-layout>
    </div>
</template>

<style lang="scss" scoped>
.contents-style {
    padding: 5px 20px;
}

.n-layout-header {
    padding: 12px;
}
.n-layout-footer {
    background: rgba(128, 128, 128, 0.2);
    padding: 24px;
}

.n-layout-sider {
    background: rgba(128, 128, 128, 0.3);
}

.n-layout-content {
    background: rgba(128, 128, 128, 0.4);
}
/* :deep(.n-layout-toggle-bar) */
.n-layout-sider {
    :deep(.n-layout-toggle-bar) {
        top: 100px;
    }
    :deep(.n-layout-toggle-button) {
        top: 100px;
    }
}
</style>
