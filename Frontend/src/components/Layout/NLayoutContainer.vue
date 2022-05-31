<script setup lang="ts">
import { CSSProperties, h, ref, reactive } from 'vue';
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
import ProductManagement from '@/views/subpages/ProductManagement.vue';

function renderIcon(icon: Component) {
    return () => h(NIcon, null, { default: () => h(icon) });
}

const dropdownOptions = [
    {
        label: '用户资料',
        key: 'profile',
        icon: renderIcon(UserIcon),
    },
    {
        label: '编辑用户资料',
        key: 'editProfile',
        icon: renderIcon(EditIcon),
    },
    {
        label: '退出登录',
        key: 'logout',
        icon: renderIcon(LogoutIcon),
    },
];

const menuOptions: MenuOption[] = [
    {
        label: '且听风吟',
        key: 'hear-the-wind-sing',
        icon: renderIcon(BookIcon),
    },
    {
        label: '1973年的弹珠玩具',
        key: 'pinball-1973',
        icon: renderIcon(BookIcon),
        disabled: true,
        children: [
            {
                label: '鼠',
                key: 'rat',
            },
        ],
    },
    {
        label: '寻羊冒险记',
        key: 'a-wild-sheep-chase',
        disabled: true,
        icon: renderIcon(BookIcon),
    },
    {
        label: '舞，舞，舞',
        key: 'dance-dance-dance1',
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
    {
        label: '舞，舞，舞',
        key: 'dance-dance-dance2',
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
    {
        label: '舞，舞，舞',
        key: 'dance-dance-dance3',
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
const collapsed = ref(false);
const activeKey = ref<string | null>(null);

const layoutStyle: CSSProperties = {
    display: 'flex',
    flexDirection: 'column',
    // flex:1
};
const layoutContentStyle: CSSProperties = {
    flex: '1',
    backgroundColor: '#f5f7fb',
    // maxWidth: "1440px",
    margin: '0 auto',
    padding: '20px 60px',
};

const layoutSiderStyle = reactive<CSSProperties>({
    padding: '40px 20px',
    transition: 'all 0.3s',
});
</script>

<style lang="scss" scoped>
.headerstyle {
    display: flex;
    width: 100%;
    padding: 10px 0;

    /* padding-right: 50px; */
    .client-icons {
        margin-left: auto;
        margin-right: 20px;
        width: fit-content;
        display: flex;
        /* flex: 0 0 0%; */
        align-items: center;

        /* justify-content: center; */
        /* justify-self: flex-end; */
        /* text-align: center; */
        > div {
            margin-left: 6px;
            display: flex;
            align-items: center;
        }
    }

    .main-logo {
        margin-left: 20px;
    }
}

.common-layout {
    height: 100%;
}

.n-layout-sider {
    background-color: #fff;
}

.layout-contents {
    background-color: #f5f7fb;
    /* max-width: 1200px */
}

.contents {
    margin-left: 20px;
    /* padding: 8px; */
    background-color: transparent;
}
</style>

<template>
    <div class="common-layout">
        <n-layout :content-style="layoutStyle" position="absolute">
            <!-- <n-switch v-model:value="collapsed" /> -->
            <n-layout-header bordered>
                <div class="headerstyle">
                    <div class="main-logo">
                        <n-card title="卡片" size="small"></n-card>
                    </div>
                    <div class="client-icons">
                        <div>
                            <n-icon size="30" color="#dbdcdc" :component="NotiIcon" />
                        </div>
                        <div>
                            <n-avatar round :size="48" src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg" />
                        </div>
                        <div>
                            <n-dropdown :options="dropdownOptions" trigger="click">
                                <n-icon size="30" color="#dbdcdc" :component="ElliIcon" />
                            </n-dropdown>
                        </div>
                    </div>
                </div>
            </n-layout-header>
            <n-layout-content has-sider :content-style="layoutContentStyle" class="layout-contents">
                <!-- :default-collapsed="false" -->
                <n-layout-sider
                    bordered
                    :width="275"
                    collapse-mode="width"
                    :collapsed-width="64"
                    :collapsed="collapsed"
                    @collapse="
                        () => {
                            collapsed = true;
                            layoutSiderStyle.padding = '40px 0';
                        }
                    "
                    @expand="
                        () => {
                            collapsed = false;
                            layoutSiderStyle.padding = '40px 20px';
                        }
                    "
                    show-trigger
                    :content-style="layoutSiderStyle"
                    :native-scrollbar="false"
                >
                    <n-menu
                        v-model:value="activeKey"
                        :collapsed="collapsed"
                        :collapsed-width="64"
                        :collapsed-icon-size="22"
                        :options="menuOptions"
                        accordion
                    />
                </n-layout-sider>

                <NLayoutContent class="contents">
                    <product-management />
                </NLayoutContent>
            </n-layout-content>
        </n-layout>
        <!-- <n-layout> -->

        <!-- </n-layout> -->
    </div>
</template>
