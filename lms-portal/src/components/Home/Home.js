import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Home.scss';

const Home = () => {
    let settings = {
        dots: true,
        speed: 300,
        draggable: false,
        lazyLoad: 'ondemand'
    };
    return (
        <div className="container-fluid home">
            <Slider {...settings}>
                <div>
                    <img className="pic" src="/slider-1.jpg" alt="slider-1" />
                </div>
                <div>
                    <img className="pic" src="/slider-2.jpg" alt="slider-2" />
                </div>
            </Slider>
        </div>
    );
};

export default Home;