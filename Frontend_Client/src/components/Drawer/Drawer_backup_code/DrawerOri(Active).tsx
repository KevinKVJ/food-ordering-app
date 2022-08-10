import { css } from '@emotion/react';
import { useEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';

import Mask from '@/components/Mask/Mask';

import type { drawerProps } from '../DrawerType';

/*
 *  onClose 关闭回调
 *  title 标题
 *  width 宽度
 *  zIndex zIndex
 *  placement 抽屉方向
 *  mask 是否展示遮罩
 *  maskClosable 点击遮罩是否关闭
 *  closable 是否显示右上角关闭按钮
 *  destroyOnClose 关闭抽屉销毁子元素
 *  getContainer 指定 Drawer 挂载的 HTML 节点, 可以将抽屉挂载在任何元素上
 *  drawerStyle 能自定义抽屉弹出层样式
 */

const DrawerInternal = ({
    children,
    activeSwitch: MountSwitch,
    drawerWidth = 300,
    transitionDuration = 0.25,
    withMask = true,
    clickMaskToClose = true,
    onClose,
    drawerContentStyle = {},
    destroyOnClose = false,
}: drawerProps) => {
    /* -----------------States------------------ */
    const [, setDrawerMount] = MountSwitch;
    const [drawerInternalActiveState, setDrawerInternalActiveState] = useState(false);
    const [drawerContentDisplay, setDrawerContentDisplay] = useState(false);

    /* -----------------Styles------------------ */
    const drawerWrapperStyle = () => css`
        position: absolute;
        top: 0;
        width: 100%;
        bottom: 0;
        overflow: hidden;
    `;
    const drawerStyle = useMemo(
        () => css`
            position: absolute;
            top: 0;
            bottom: 0;
            right: ${drawerInternalActiveState ? 0 : -drawerWidth}px;
            z-index: 1000;
            width: ${drawerWidth}px;
            background-color: #fff;
            /* transition: right linear ${transitionDuration}s; */
            transition: right ease-in-out ${transitionDuration}s;
        `,
        [drawerInternalActiveState]
    );

    useEffect(() => {
        setDrawerInternalActiveState(true);
        return () => {
            !!onClose && onClose();
        };
    }, []);

    const transitionEnd = () => {
        !drawerInternalActiveState && setDrawerMount(false);
    };

    return createPortal(
        <div className='drawer-wrapper' css={drawerWrapperStyle}>
            {withMask && drawerInternalActiveState && (
                <Mask
                    onClick={() =>
                        clickMaskToClose && setDrawerInternalActiveState(false)
                    }
                />
            )}
            <div className='drawer' css={drawerStyle} onTransitionEnd={transitionEnd}>
                <div
                    className='drawer-children'
                    style={{ width: '100%', ...drawerContentStyle }}>
                    {children}
                </div>
            </div>
        </div>,
        document.body
    );
};

const Drawer = ({ activeSwitch: MountSwitch, ...props }: drawerProps) => {
    const [drawerMount] = MountSwitch;

    return drawerMount ? <DrawerInternal activeSwitch={MountSwitch} {...props} /> : null;
};

export default Drawer;
