import { css } from '@emotion/react';
import { useCallback, useEffect, useLayoutEffect, useMemo, useState } from 'react';
import { createPortal } from 'react-dom';

import Mask from '@/components/Mask/Mask';

import type { drawerProps } from './DrawerType';

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
    drawerWidth = 300,
    transitionDuration = 0.25,
    withMask = true,
    clickMaskToClose = true,
    onClose,
    drawerContentStyle = {},
    destroyOnClose = false,
}: Omit<drawerProps, 'activeSwitch'>) => {
    /* -----------------States------------------ */
    const [drawerInternalActiveState, setDrawerInternalActiveState] = useState(false);
    // const [drawerContentDisplay, setDrawerContentDisplay] = useState(false);
    /* -----------------Styles------------------ */
    const drawerWrapperStyle = () => css`
        /* position: absolute;
        top: 0;
        width: 100%;
        bottom: 0;
        overflow: hidden; */
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
            transition: right ease-in-out ${transitionDuration}s;
        `,
        [drawerInternalActiveState]
    );

    useLayoutEffect(() => {
        setDrawerInternalActiveState(() => true);
        // console.log('LayoutEffect Internal');
        console.log('Effect Internal');
    }, []);

    const handleClose = () => {
        if (!drawerInternalActiveState) {
            !!onClose && onClose();
        }
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
            <div className='drawer' css={drawerStyle} onTransitionEnd={handleClose}>
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

const Drawer = ({
    activeSwitch: MountSwitch,
    keepMounted = true,
    onClose,
    ...props
}: drawerProps) => {
    const [drawerState, setDrawerState] = MountSwitch;
    const [drawerMount, setDrawerMount] = useState(false);

    useEffect(() => {
        if (drawerState) {
            setDrawerMount(true);
        }
        // drawerState && setDrawerMount(true);
        console.log('Effect External');
    }, [drawerState]);

    // useLayoutEffect(() => {
    //     console.log('LayoutEffect External');
    // }, [drawerState]);

    const unmountAndOnClose = useCallback(() => {
        console.log('666');
        !keepMounted && setDrawerMount(false);
        !!onClose && onClose();
        setDrawerState(false);
    }, [drawerState, drawerMount]);

    return drawerMount ? (
        <DrawerInternal
            // activeSwitch={MountSwitch}
            onClose={unmountAndOnClose}
            {...props}
        />
    ) : null;
};

export default Drawer;
