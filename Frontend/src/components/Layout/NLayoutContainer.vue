<script setup lang="tsx">
import { CSSProperties, h, ref, reactive } from 'vue';
import type { Component } from 'vue';
import { NIcon } from 'naive-ui';
import type { MenuOption } from 'naive-ui';
import {
    Notifications as NotiIcon,
    EllipsisVertical as ElliIcon,
    PersonCircleOutline as UserIcon,
    Pencil as EditIcon,
    LogOutOutline as LogoutIcon,
} from '@vicons/ionicons5';
// import ProductManagement from '@/views/subpages/ProductManagement.vue';

function renderIcon(icon: Component) {
    // return () => h(NIcon,null, { default: () => h(icon) });
    // return () => h(NIcon, h(icon));
    // const Icon = h(icon);
    return () => <NIcon component={icon}></NIcon>;
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

const collapsed = ref(false);

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
                    :width="250"
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
                    
                    <slot name="sider" :isCollapsed="collapsed"></slot>
                </n-layout-sider>

                <NLayoutContent class="contents">
                    <slot name="contents"></slot>
                    
                </NLayoutContent>
            </n-layout-content>
        </n-layout>
        <!-- <n-layout> -->

        <!-- </n-layout> -->
    </div>
</template>
