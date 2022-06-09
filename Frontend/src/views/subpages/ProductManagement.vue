<script setup lang="tsx">
import { reactive, ref } from 'vue';
import type { DataTableColumns,PaginationProps } from 'naive-ui';
import type { productData } from './ProductDataTypes';
import http from '@/http/request';
import { Random } from 'mockjs';

// const aaa = reactive()

const columns: DataTableColumns = [
    {
        type: 'selection',
        // width: '15',
        fixed: 'left',
    },
    {
        title: 'Image',
        key: 'image',
        width: 80,
        fixed: 'left',
        render(rowObj,index) {
            return <n-avatar bordered size={48} src={Random.dataImage('400x400', `hello product ${index}`)} />;
        },
        align:'center'
    },
    {
        title: 'ID',
        key: 'id',
        align: 'center',
        width: '80',
        // fixed: 'left',
        // render(row){
        //     return <div>{row.id}</div>
        // }
    },
    {
        title: 'Name',
        key: 'name',
        // width: '150',
        // fixed: 'left',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{`row ${index}`}</span>;
        // },
        // ellipsis: true,
    },
    {
        title: 'Price',
        key: 'price',
        // width: 80,
        // fixed: 'left',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{row.price}</span>;
        // },
    },
    {
        title: 'Monthly sales',
        key: 'monthly',
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{`row ${index}`}</span>;
        // },
        // width: 80,
    },
    // {
    //     title: 'Options',
    //     key: 'options',
    //     width: 80,
    //     render(row: any, index: number) {
    //         return <span>{`row ${index}`}</span>;
    //     },
    // },
    {
        title: 'Inventory',
        key: 'inventory',
        // render(row: any, index: number) {
        //     return <span>{`row ${index}`}</span>;
        // },
        // width: 80,
        // fixed: 'right',
    },
    {
        title: 'Discount',
        key: 'discount',
        // width: 80
        // render(row: any, index: number) {
        //     // return h('span', ['row ', index]);
        //     return <span>{index}</span>;
        // },
        // fixed: 'right',
    },
];

const data = ref<productData[]>();
http.get('/api/v1/product/all').then((req) => {
    const reqData: productData[] = req.data.data;
    if (!reqData) {
        console.error('Data is invalid or empty, Please check!');
        return;
    }
    data.value = reqData;
    // console.log(reqData);
});

const pagination:PaginationProps = { pageSize: 15 } as PaginationProps;
</script>

<template>
    <div>
        <n-layout has-sider siderPlacement="right" position="absolute">
            <!-- :content-style="{display:'flex',flexFlow:'column nowrap'}" -->
            <n-layout embedded class="contents-style" :content-style="{ display: 'flex', flexFlow: 'column nowrap' }">
                <n-layout-header :style="{ backgroundColor: 'transparent' }">
                    <h1>Products Management</h1>
                    <n-space>
                        <n-button type="info">Oops!</n-button>
                        <n-button type="info">Oops!</n-button>
                        <n-button type="info">Oops!</n-button>
                        <n-button type="error">Delete</n-button>
                    </n-space>
                </n-layout-header>
                <n-layout-content :style="{ backgroundColor: 'transparent', flex: 1 }">
                    <n-data-table
                        :columns="columns"
                        :data="data"
                        :pagination="pagination"
                        :style="{ height: '100%' }"
                        flex-height
                        :scroll-x="1000"
                        :row-key="(obj:productData) => obj.id"
                        :single-line="false"
                    />
                </n-layout-content>
                <!-- <n-layout-footer>成府路</n-layout-footer> -->
                <!-- :max-height="250"  height: `100%`-->
            </n-layout>
            <n-layout-sider content-style="padding: 24px;" show-trigger :style="{ backgroundColor: 'transparent' }" class="category-sider">
                <categories-list />
            </n-layout-sider>
        </n-layout>
    </div>
</template>

<style lang="scss" scoped>
.contents-style {
    padding: 30px;
}

.n-layout-header,
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
