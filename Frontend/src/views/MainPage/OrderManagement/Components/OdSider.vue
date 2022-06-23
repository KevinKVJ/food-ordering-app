<template>
    <div class="wrapper">
        <!-- :style="{ height: '100%' }" -->
        <n-card closable @close="emits('closeSider')" :header-style="{ padding: '40px 25px 0 25px' }">
            <template #header> <h2 :style="{ fontWeight: 600, margin: '0' }">Order No: 0001</h2> </template>

            <template #header-extra>
                <h3 :style="{ fontWeight: 600, margin: '0', marginRight: '20px' }">Status: Pending</h3>
            </template>
            <template #default>
                <n-divider />
                <!--  title="Order Details:" -->
                <n-descriptions label-placement="left" :column="1">
                    <!-- <n-descriptions-item>
                        <template #label> 早餐 </template>
                        苹果
                    </n-descriptions-item> -->
                    <n-descriptions-item label="Client Name"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Address"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Phone"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Due Time"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Delivery Method"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Comment"> 苹果 </n-descriptions-item>
                    <n-descriptions-item label="Products"> 苹果 </n-descriptions-item>
                </n-descriptions>
                <!-- <n-descriptions label-placement="top" :column="4">
                    <n-descriptions-item></n-descriptions-item>
                </n-descriptions> -->
                <n-divider />
                <n-data-table :columns="columns" :data="props.itemsData" :pagination="5" :bordered="false" />
            </template>
        </n-card>
    </div>
</template>

<script setup lang="tsx">
import type { orderProductData } from '../OdDataTypes';
import { DataTableColumns } from 'naive-ui';

const props = defineProps<{
    itemsData: orderProductData[];
}>();

const emits = defineEmits<{
    (e: 'closeSider'): void;
}>();

const handleClose = () => {
    emits('closeSider');
};

const columns: DataTableColumns<orderProductData> = [
    {
        title: 'Item',
        key: 'name',
    },
    {
        title: 'Amount',
        key: 'count',
    },
    {
        title: 'Price',
        key: 'price',
    },
    {
        title: 'Options',
        key: 'options',
    },
];
</script>

<style scoped lang="scss">
/* .wrapper {
    height: 100%;
} */
</style>
