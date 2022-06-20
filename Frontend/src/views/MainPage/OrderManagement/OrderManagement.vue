<script setup lang="ts">

</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <!-- :content-style="{display:'flex',flexFlow:'column nowrap'}" -->
            <n-layout embedded class="contents-style" :content-style="{ display: 'flex', flexFlow: 'column nowrap' }">
                <n-layout-header :style="{ backgroundColor: 'transparent' }">
                    <h2>Orders Management</h2>
                    <n-space>
                        <n-button type="info">Add</n-button>
                        <n-button type="error">{{
                            `Delete`
                        }}</n-button>
                    </n-space>
                </n-layout-header>
                <n-layout-content :style="{ backgroundColor: 'transparent', flex: 1 }">
                    <!-- <ProdDataTable :tableData="tableData" @delete-data-row="handleDelete" v-model:checked-rows="checkedRowKeysRef" /> -->
                    <ODDataTable />
                </n-layout-content>
            </n-layout>

            <!-- class="category-sider" -->
            <n-layout-sider
                :content-style="{ padding: '0 18px' }"
                show-trigger
                :style="{ backgroundColor: 'transparent' }"
                width="400"
                :native-scrollbar="false"
            >
                    <!-- v-model:category-data="categories"
                    @get-prods-by-category="handleGetProdsByCategory"
                    @get-all-prods="handleGetAllProds" -->
                <!-- <ProdCategoriesSider /> -->
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