<script setup lang="tsx">
import { onMounted, ref } from 'vue';
import type { productData, productCategory } from './ProductDataTypes';
// import http from '@/http/request';
import { apiInsertAProduct, apiGetAllProducts, apiGetAllCategoies, apiGetProductsByCategoryId } from './ProductManagementAPIs';

const categories = ref<productCategory[]>([]);
const tableData = ref<productData[]>([]);

const _log = (item: any) => console.log(item);
onMounted(() => {
    apiGetAllProducts()
        .then(req => {
            const reqData: productData[] = req.data.data;
            if (!reqData) {
                throw new Error('Data is invalid or empty, Please check!');
            }

            tableData.value = reqData;
        })
        .catch(err => {
            console.error(err);
        });

    apiGetAllCategoies().then(req => {
        const reqData: productCategory[] = req.data.data;
        if (!reqData) {
            console.error('Data is invalid or empty, Please check!');
            return;
        }
        categories.value = reqData;
    });
});

const handleAddProductModal = () => (showAdd.value = true);
const showAdd = ref(false);
const formAdd = ref({
    id: 0,
    name: '',
    price: 0,
    inventory: 0,
    discount: 0,
    monthly: 0,
    merchantId: 0,
});
const showAddLoading = ref(false);

const handleAddProduct = async () => {
    // formAdd.value['id'] = 4112;
    // tableData.value.push(formAdd.value);
    showAddLoading.value = true;
    try {
        await apiInsertAProduct(formAdd.value);
        const getAllRes = await apiGetAllProducts();
        tableData.value = getAllRes.data.data;
    } catch (error) {
        console.error(error);
        alert('Error!!!');
    }

    // setTimeout(() => {
    //     formAdd.value = {
    //         id: 0,
    //         name: '',
    //         price: 0,
    //         inventory: 0,
    //         discount: 0,
    //         monthly: 0,
    //     };
    // }, 3000);
    setTimeout(() => {
        showAddLoading.value = false;
    }, 2000);
    setTimeout(() => {
        showAdd.value = false;
    }, 2000);
};

const handleGetProdsByCategory = async (id: number) => {
    try {
        const reqData = await apiGetProductsByCategoryId({ id });
        tableData.value = reqData.data.data;
    } catch (err) {
        console.error(err);
    }
};

const handleGetAllProds = async () => {
    try {
        const reqData = await apiGetAllProducts();
        tableData.value = reqData.data.data;
    } catch (err) {
        console.error(err);
    }
};

const checkedRowKeysRef = ref<(string | number)[]>([]);
const handleDelete = (ids: (string | number)[]) => {
    if (ids.length === 0) {
        return;
    }
    try {
        const aaa = tableData.value.filter((item, index) => !ids.includes(item.id));
        tableData.value = aaa;
        checkedRowKeysRef.value = [];
        // TODO API adding
    } catch (err) {
        console.error(err);
    }
};

// const handleCheckedRow = (rowKeys: (string | number)[]) => {
//     checkedRowKeysRef.value = rowKeys;
// };
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <!-- :content-style="{display:'flex',flexFlow:'column nowrap'}" -->
            <n-layout embedded class="contents-style" :content-style="{ display: 'flex', flexFlow: 'column nowrap' }">
                <n-layout-header :style="{ backgroundColor: 'transparent' }">
                    <h2>Products Management</h2>
                    <n-space>
                        <n-button type="info" @click="handleAddProductModal">Add</n-button>
                        <n-button type="error" @click="handleDelete(checkedRowKeysRef)" v-if="!(checkedRowKeysRef.length === 0)">{{
                            `Delete(${checkedRowKeysRef.length})`
                        }}</n-button>
                    </n-space>
                </n-layout-header>
                <n-layout-content :style="{ backgroundColor: 'transparent', flex: 1 }">
                    <ProdDataTable :tableData="tableData" @delete-data-row="handleDelete" v-model:checked-rows="checkedRowKeysRef" />
                </n-layout-content>
            </n-layout>

            <!-- class="category-sider" -->
            <n-layout-sider
                :content-style="{ padding: '0 18px' }"
                show-trigger
                :style="{ backgroundColor: 'transparent' }"
                width="170"
                :native-scrollbar="false"
            >
                <ProdCategoriesSider
                    v-model:category-data="categories"
                    @get-prods-by-category="handleGetProdsByCategory"
                    @get-all-prods="handleGetAllProds"
                />
            </n-layout-sider>
        </n-layout>

        <n-modal v-model:show="showAdd" :mask-closable="false">
            <n-spin :show="showAddLoading">
                <template #description> Adding... </template>
                <n-card
                    closable
                    @close="showAdd = false"
                    style="width: 600px"
                    :bordered="false"
                    size="huge"
                    role="dialog"
                    aria-modal="true"
                >
                    <template #header>Add New Product</template>
                    <!--  model="formAdd" -->
                    <n-form size="medium" :label-width="80">
                        <n-form-item label="Product Name">
                            <n-input v-model:value="formAdd.name" placeholder="Name" />
                        </n-form-item>
                        <n-form-item label="Price">
                            <n-input v-model.number:value="formAdd.price" placeholder="Price" />
                        </n-form-item>
                        <n-form-item label="Inventory">
                            <n-input v-model.number:value="formAdd.inventory" placeholder="Inventory" />
                        </n-form-item>
                        <!--  @click="handleValidateClick" -->
                        <n-grid :cols="24" :x-gap="8">
                            <n-form-item-gi :span="20" label="Discount">
                                <!-- v-model:value="model.sliderValue" -->
                                <n-slider :step="1" v-model:value="formAdd.discount" :format-tooltip="(value: number) => `${value}%`" />
                                <!-- v-model:value="value"  -->
                            </n-form-item-gi>
                            <n-form-item-gi :span="4">
                                <n-input type="text" disabled placeholder="" :value="`${formAdd.discount}%`" />
                            </n-form-item-gi>
                        </n-grid>
                        <n-form-item label="Categories(Tags)" size="large">
                            <!-- v-model:value="formAdd.multipleSelectValue" :options="generalOptions"-->
                            <n-select placeholder="Select" multiple />
                        </n-form-item>
                        <div :style="{ display: 'flex', justifyContent: 'center' }">
                            <n-space>
                                <n-button type="success" @click="handleAddProduct">Confirm</n-button>
                                <n-button attr-type="button" @click="showAdd = false">Cancel</n-button>
                            </n-space>
                        </div>
                        {{ formAdd }}
                    </n-form>
                    <!-- <template #footer> 尾部 </template> -->
                </n-card>
            </n-spin>
        </n-modal>
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
