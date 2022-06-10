<script setup lang="tsx">
import { ref } from 'vue';
import type { productData } from './ProductDataTypes';
import http from '@/http/request';

const tableData = ref<productData[]>();
http.get('/api/v1/product/all').then((req) => {
    const reqData: productData[] = req.data.data;
    if (!reqData) {
        console.error('Data is invalid or empty, Please check!');
        return;
    }
    tableData.value = reqData;
    // console.log(reqData);
});

const handleAddProduct = () => (showAdd.value = true);
const showAdd = ref(false);
const formAdd = ref({
    user: {
        name: '',
        age: '',
    },
    phone: '',
});
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <!-- :content-style="{display:'flex',flexFlow:'column nowrap'}" -->
            <n-layout embedded class="contents-style" :content-style="{ display: 'flex', flexFlow: 'column nowrap' }">
                <n-layout-header :style="{ backgroundColor: 'transparent' }">
                    <h2>Products Management</h2>
                    <n-space>
                        <n-button type="info" @click="handleAddProduct">Add</n-button>
                        <!-- <n-button type="info">Oops!</n-button>
                        <n-button type="info">Oops!</n-button> -->
                        <n-button type="error">Delete</n-button>
                    </n-space>
                </n-layout-header>
                <n-layout-content :style="{ backgroundColor: 'transparent', flex: 1 }">
                    <ProdDataTable :tableData="tableData"/>
                </n-layout-content>
            </n-layout>

            <n-layout-sider :content-style="{padding: '24px'}" show-trigger :style="{ backgroundColor: 'transparent' }" class="category-sider">
                <ProdCategoriesSider />
            </n-layout-sider>

        </n-layout>

        <n-modal v-model:show="showAdd" :mask-closable="false">
            <n-card closable @close="showAdd = false" style="width: 600px" :bordered="false" size="huge" role="dialog" aria-modal="true">
                <template #header>Add New Product</template>
                <n-form size="medium" model="formAdd" :label-width="80">
                    <n-form-item label="Product Name" path="user.name">
                        <n-input v-model:value="formAdd.user.name" placeholder="Name" />
                    </n-form-item>
                    <n-form-item label="Price" path="user.age">
                        <n-input v-model:value="formAdd.user.age" placeholder="Price" />
                    </n-form-item>
                    <n-form-item label="Inventory" path="phone">
                        <n-input v-model:value="formAdd.phone" placeholder="Inventory" />
                    </n-form-item>
                    <!--  @click="handleValidateClick" -->
                    <n-grid :cols="24" :x-gap="8">
                        <n-form-item-gi :span="20" label="Discount">
                            <!-- v-model:value="model.sliderValue" -->
                            <n-slider :step="1" :format-tooltip="(value: number) => `${value}%`" />
                            <!-- v-model:value="value"  -->
                        </n-form-item-gi>
                        <n-form-item-gi :span="4">
                            <n-input type="text" disabled placeholder="" />
                        </n-form-item-gi>
                    </n-grid>
                    <n-form-item label="Categories(Tags)" size="large">
                        <!-- v-model:value="formAdd.multipleSelectValue" :options="generalOptions"-->
                        <n-select placeholder="Select" multiple />
                    </n-form-item>
                    <div :style="{ display: 'flex', justifyContent: 'center' }">
                        <n-space>
                            <n-button type="success">Confirm</n-button>
                            <n-button attr-type="button" @click="showAdd = false">Cancel</n-button>
                        </n-space>
                    </div>
                </n-form>
                <!-- <template #footer> 尾部 </template> -->
            </n-card>
        </n-modal>
    </div>
</template>

<style lang="scss" scoped>
.contents-style {
    padding: 20px;
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
