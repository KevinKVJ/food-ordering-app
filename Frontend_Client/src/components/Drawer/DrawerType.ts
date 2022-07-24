import type { Dispatch, SetStateAction } from 'react';
import { PropsWithChildren, useState } from 'react';

type activeSwitchUseStateType =
    | ReturnType<typeof useState<boolean>>
    | [
          boolean,
          ((arg0: boolean) => void) | Dispatch<SetStateAction<boolean>>
      ];
// eslint-disable-next-line prettier/prettier

export interface drawerProps extends PropsWithChildren {
    activeSwitch: activeSwitchUseStateType;
    drawerWidth?: number;
    onClose?: () => void;
    transitionDuration?: number;
    withMask?: boolean;
    clickMaskToClose?: boolean;
    keepMounted?: boolean;
}
