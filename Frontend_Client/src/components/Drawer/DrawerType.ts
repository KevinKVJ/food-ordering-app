import type { CSSProperties, Dispatch, SetStateAction } from 'react';
import { PropsWithChildren, useState } from 'react';

type activeSwitchUseStateType =
    | ReturnType<typeof useState<boolean>>
    | [boolean, Dispatch<SetStateAction<boolean>>];

export interface drawerProps extends PropsWithChildren {
    activeSwitch: activeSwitchUseStateType;
    drawerWidth?: number;
    onClose?: () => void;
    transitionDuration?: number;
    withMask?: boolean;
    clickMaskToClose?: boolean;
    keepMounted?: boolean;
    drawerContentStyle?: CSSProperties;
    destroyOnClose?: boolean;
}
