<script setup lang="tsx">
import { ref } from 'vue';
import type { Component } from 'vue';
import { NIcon } from 'naive-ui';
import type { MenuOption } from 'naive-ui';
import {
    BookOutline as BookIcon,
    PersonOutline as PersonIcon,
    WineOutline as WineIcon,
    Notifications as NotiIcon,
    EllipsisVertical as ElliIcon,
    PersonCircleOutline as UserIcon,
    Pencil as EditIcon,
    LogOutOutline as LogoutIcon,
} from '@vicons/ionicons5';

function renderIcon(icon: Component) {
    return () => <NIcon component={icon}></NIcon>;
}

const getLabel = (label: string, to: string |{ name: string; params?: object }) => () =>
    (
        <router-link to={to}>
            <span style={{ fontSize: '15px', letterSpacing: '0.3px' }}>{label}</span>
        </router-link>
    );
const menuOptions: MenuOption[] = [
    {
        label: getLabel('Products','/prodm'),
        key: 'products',
        icon: renderIcon(BookIcon),
    },
    {
        label: getLabel('Orders','/odm'),
        key: 'orders',
        icon: renderIcon(BookIcon),
    },
    {
        label: getLabel('Shopping Cart','/login'),
        key: 'shopping-cart',
        icon: renderIcon(BookIcon),
    },
    {
        label: getLabel('Discounts','/signup'),
        key: 'discounts',
        icon: renderIcon(BookIcon),
    },
    // {
    //     label: getLabel('Customer Reviews'),
    //     key: 'customer-reviews',
    //     icon: renderIcon(BookIcon),
    // },
    // {
    //     label: '1973年的弹珠玩具',
    //     key: 'pinball-1973',
    //     icon: renderIcon(BookIcon),
    //     disabled: true,
    //     children: [
    //         {
    //             label: '鼠',
    //             key: 'rat',
    //         },
    //     ],
    // },
    // {
    //     label: '寻羊冒险记',
    //     key: 'a-wild-sheep-chase',
    //     disabled: true,
    //     icon: renderIcon(BookIcon),
    // },
    {
        label: '舞，舞，舞',
        key: 'dance-dance-dance',
        icon: renderIcon(BookIcon),
        children: [
            {
                type: 'group',
                label: '人物',
                key: 'people',
                children: [
                    {
                        label: '叙事者',
                        key: 'narrator',
                        icon: renderIcon(PersonIcon),
                    },
                    {
                        label: '羊男',
                        key: 'sheep-man',
                        icon: renderIcon(PersonIcon),
                    },
                ],
            },
            {
                label: '饮品',
                key: 'beverage',
                icon: renderIcon(WineIcon),
                children: [
                    {
                        label: '威士忌',
                        key: 'whisky',
                    },
                ],
            },
            {
                label: '食物',
                key: 'food',
                children: [
                    {
                        label: '三明治',
                        key: 'sandwich',
                    },
                ],
            },
            {
                label: '过去增多，未来减少',
                key: 'the-past-increases-the-future-recedes',
            },
        ],
    },
];
const activeKey = ref<string | null>(null);
</script>

<template>
    <NLayoutContainer>
        <template #sider="{ isCollapsed }">
            <n-menu
                v-model:value="activeKey"
                :collapsed="isCollapsed"
                :collapsed-width="64"
                :collapsed-icon-size="22"
                :options="menuOptions"
                accordion
            />
        </template>
        <template #contents>
            <router-view />
        </template>
    </NLayoutContainer>
</template>

<!-- <style lang="scss" scoped>

</style> -->
