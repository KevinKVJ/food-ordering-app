import { PropsWithChildren, useMemo } from 'react';
import Slider, { Settings } from 'react-slick';
import 'slick-carousel/slick/slick-theme.scss';
import 'slick-carousel/slick/slick.scss';

/* Really appreciate the react-slick dev group */
interface SwiperProps extends PropsWithChildren<Settings> {}

const Swiper: React.FC<SwiperProps> = ({ children, dots = true, speed = 500, ...swiperSettings }) => {
    const settings = useMemo(
        () => ({
            dots,
            speed,
            // infinite: true,
            // slidesToShow: 3,
            // slidesToScroll: 3,
            ...swiperSettings,
        }),
        [swiperSettings]
    );
    return (
        <div>
            {/* <h2> Single Item</h2> */}
            <Slider {...settings}>{children}</Slider>
        </div>
    );
};

export default Swiper;
