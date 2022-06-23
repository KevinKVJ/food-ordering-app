<template>
    <div class="wrapper">
        <!-- :style="{ height: '100%' }" -->
        <n-card closable @close="emits('closeSider')" :header-style="{ padding: '40px 25px 0 25px' }">
            <template #header> <h2 :style="{ fontWeight: 600, margin: '0' }">Order No: 0001</h2> </template>

            <template #header-extra>
                <h3 :style="{ fontWeight: 600, margin: '0', marginRight: '20px' }">Status: {{ statusDescription }}</h3>
            </template>
            <template #default>
                <n-divider />
                <!--  title="Order Details:" -->
                <n-descriptions label-placement="left" :column="1">
                    <!-- <n-descriptions-item>
                        <template #label> 早餐 </template>
                        苹果
                    </n-descriptions-item> -->
                    <n-descriptions-item label="Client Name">{{clientName}}</n-descriptions-item>
                    <n-descriptions-item label="Address">{{address}} </n-descriptions-item>
                    <n-descriptions-item label="Phone">{{phone}}</n-descriptions-item>
                    <n-descriptions-item label="Due Time"> {{due}} </n-descriptions-item>
                    <n-descriptions-item label="Delivery Method"> {{deliveryMethod}} </n-descriptions-item>
                    <n-descriptions-item label="Comment">{{comment}}</n-descriptions-item>
                </n-descriptions>
                <!-- <n-descriptions label-placement="top" :column="4">
                    <n-descriptions-item></n-descriptions-item>
                </n-descriptions> -->
                <n-divider />
                <n-data-table :columns="columns" :data="props.OrderDetail?.products" :pagination="{ pageSize: 15 }" :bordered="false" />
            </template>
        </n-card>
    </div>
</template>

<script setup lang="tsx">
import type { orderData, orderProductData } from '../OdDataTypes';
import { DataTableColumns } from 'naive-ui';
import {unref,reactive, toRefs, toRef, watchEffect ,onMounted, PropType, ToRefs,Ref} from 'vue';
import { computed} from '@vue/reactivity';

const props = defineProps<{
    OrderDetail?: orderData;
}>();
const {OrderDetail}= toRefs(props);

// const {id, address, clientName, phone, comment, due, statusDescription, totalPrice, products, deliveryMethod} = {...OrderDetail} as orderData
//     const { id, address, clientName, phone, comment, due, statusDescription, totalPrice, products, deliveryMethod} = OrderDetail || {};
// const {id, address, clientName, phone, comment, due, statusDescription, totalPrice, products, deliveryMethod} = computed(() => {
//     if(typeof OrderDetail !== 'undefined'){
//         const decon = unref(OrderDetail)
//         if(typeof decon !== 'undefined'){
//             return decon;
//         }
//         return {} as orderData;
//     }
//     return {} as any;
// })

const {id, address, clientName, phone, comment, due, statusDescription, totalPrice, products, deliveryMethod} = OrderDetail ? (OrderDetail.value ? reactive(OrderDetail) : reactive({} as orderData)) : reactive({} as orderData);

onMounted(() => {
    console.log(props.OrderDetail);
})



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
