<script setup lang="ts">
import { CSSProperties, h, ref, reactive } from "vue";
import type { Component } from "vue";
import {
    NIcon,
} from "naive-ui";
import type { MenuOption } from "naive-ui";
import {
    BookOutline as BookIcon,
    PersonOutline as PersonIcon,
    WineOutline as WineIcon,
} from "@vicons/ionicons5";

function renderIcon(icon: Component) {
    return () => h(NIcon, null, { default: () => h(icon) });
}



const menuOptions: MenuOption[] = [
    {
        label: "且听风吟",
        key: "hear-the-wind-sing",
        icon: renderIcon(BookIcon),
    },
    {
        label: "1973年的弹珠玩具",
        key: "pinball-1973",
        icon: renderIcon(BookIcon),
        disabled: true,
        children: [
            {
                label: "鼠",
                key: "rat",
            },
        ],
    },
    {
        label: "寻羊冒险记",
        key: "a-wild-sheep-chase",
        disabled: true,
        icon: renderIcon(BookIcon),
    },
    {
        label: "舞，舞，舞",
        key: "dance-dance-dance1",
        icon: renderIcon(BookIcon),
        children: [
            {
                type: "group",
                label: "人物",
                key: "people",
                children: [
                    {
                        label: "叙事者",
                        key: "narrator",
                        icon: renderIcon(PersonIcon),
                    },
                    {
                        label: "羊男",
                        key: "sheep-man",
                        icon: renderIcon(PersonIcon),
                    },
                ],
            },
            {
                label: "饮品",
                key: "beverage",
                icon: renderIcon(WineIcon),
                children: [
                    {
                        label: "威士忌",
                        key: "whisky",
                    },
                ],
            },
            {
                label: "食物",
                key: "food",
                children: [
                    {
                        label: "三明治",
                        key: "sandwich",
                    },
                ],
            },
            {
                label: "过去增多，未来减少",
                key: "the-past-increases-the-future-recedes",
            },
        ],
    },
    {
        label: "舞，舞，舞",
        key: "dance-dance-dance2",
        icon: renderIcon(BookIcon),
        children: [
            {
                type: "group",
                label: "人物",
                key: "people",
                children: [
                    {
                        label: "叙事者",
                        key: "narrator",
                        icon: renderIcon(PersonIcon),
                    },
                    {
                        label: "羊男",
                        key: "sheep-man",
                        icon: renderIcon(PersonIcon),
                    },
                ],
            },
            {
                label: "饮品",
                key: "beverage",
                icon: renderIcon(WineIcon),
                children: [
                    {
                        label: "威士忌",
                        key: "whisky",
                    },
                ],
            },
            {
                label: "食物",
                key: "food",
                children: [
                    {
                        label: "三明治",
                        key: "sandwich",
                    },
                ],
            },
            {
                label: "过去增多，未来减少",
                key: "the-past-increases-the-future-recedes",
            },
        ],
    },
    {
        label: "舞，舞，舞",
        key: "dance-dance-dance3",
        icon: renderIcon(BookIcon),
        children: [
            {
                type: "group",
                label: "人物",
                key: "people",
                children: [
                    {
                        label: "叙事者",
                        key: "narrator",
                        icon: renderIcon(PersonIcon),
                    },
                    {
                        label: "羊男",
                        key: "sheep-man",
                        icon: renderIcon(PersonIcon),
                    },
                ],
            },
            {
                label: "饮品",
                key: "beverage",
                icon: renderIcon(WineIcon),
                children: [
                    {
                        label: "威士忌",
                        key: "whisky",
                    },
                ],
            },
            {
                label: "食物",
                key: "food",
                children: [
                    {
                        label: "三明治",
                        key: "sandwich",
                    },
                ],
            },
            {
                label: "过去增多，未来减少",
                key: "the-past-increases-the-future-recedes",
            },
        ],
    },
    {
        label: "舞，舞，舞",
        key: "dance-dance-dance",
        icon: renderIcon(BookIcon),
        children: [
            {
                type: "group",
                label: "人物",
                key: "people",
                children: [
                    {
                        label: "叙事者",
                        key: "narrator",
                        icon: renderIcon(PersonIcon),
                    },
                    {
                        label: "羊男",
                        key: "sheep-man",
                        icon: renderIcon(PersonIcon),
                    },
                ],
            },
            {
                label: "饮品",
                key: "beverage",
                icon: renderIcon(WineIcon),
                children: [
                    {
                        label: "威士忌",
                        key: "whisky",
                    },
                ],
            },
            {
                label: "食物",
                key: "food",
                children: [
                    {
                        label: "三明治",
                        key: "sandwich",
                    },
                ],
            },
            {
                label: "过去增多，未来减少",
                key: "the-past-increases-the-future-recedes",
            },
        ],
    },
];
const collapsed = ref(false);
const activeKey = ref<string | null>(null);

const layoutStyle: CSSProperties = {
    display: "flex",
    flexDirection: "column",
    // flex:1
};
const layoutContentStyle: CSSProperties = {
    flex: "1",
    backgroundColor: "#f5f7fb",
    // maxWidth: "1440px",
    margin: "0 auto",
    padding: "20px 60px",
};

const layoutSiderStyle = reactive<CSSProperties>({
    padding: "40px 20px",
    transition:"all 0.3s"
});
</script>

<style lang="scss" scoped>
.lalala {
    display: flex;
    justify-content: center;
    padding: 20px;
    /* text-align: center; */
}
.common-layout {
    height: 100%;
}
.n-layout-sider {
    background-color: #fff;
}
/* .n-menu {
    height: 100%;
} */
.layout-contents {
    background-color: #f5f7fb;
    /* max-width: 1200px */
}

.contents {
    margin-left: 20px;
}
</style>

<template>
    <div class="common-layout">
        <n-layout :content-style="layoutStyle" position="absolute">
            <!-- <n-switch v-model:value="collapsed" /> -->
            <n-layout-header bordered>
                <div class="lalala">LALALA</div>
            </n-layout-header>
            <n-layout-content
                has-sider
                :content-style="layoutContentStyle"
                class="layout-contents"
            >
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
                    />
                </n-layout-sider>

                <n-layout-content class="contents">
                    <span>内容</span>
                </n-layout-content>
            </n-layout-content>
        </n-layout>
        <!-- <n-layout> -->

        <!-- </n-layout> -->
    </div>
</template>
