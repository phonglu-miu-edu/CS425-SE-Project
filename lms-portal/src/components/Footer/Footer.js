import './Footer.scss';
import { Link } from 'react-router-dom';

const Footer = () => {
    return (
        <footer>
            <div className="footer-top-area section_50">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-3 col-xs-3 col-sm-6">
                            <div className="single-footer-widget footer-logo-widget">
                                <div className="footer-widget-text">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt labore et dolore magna aliqua.</p>
                                    <ul className="footer-social">
                                        <li><Link to="#"><i className="fa fa-facebook"></i></Link></li>
                                        <li><Link to="#"><i className="fa fa-twitter"></i></Link></li>
                                        <li><Link to="#"><i className="fa fa-linkedin"></i></Link></li>
                                        <li><Link to="#"><i className="fa fa-youtube"></i></Link></li>
                                        <li><Link to="#"><i className="fa fa-skype"></i></Link></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-3 col-xs-3 col-sm-6">
                            <div className="single-footer-widget">
                                <h3>latest post</h3>
                                <div className="latest-post-footer clearfix">
                                    <div className="latest-post-footer-left">
                                        <i className="fa fa-image"></i>
                                    </div>
                                    <div className="latest-post-footer-right">
                                        <h4><Link to="#">Your Guide to having a Harry Potter world</Link></h4>
                                        <p>Oct 15, 2023</p>
                                    </div>
                                </div>
                                <div className="latest-post-footer clearfix">
                                    <div className="latest-post-footer-left">
                                        <i className="fa fa-briefcase"></i>
                                    </div>
                                    <div className="latest-post-footer-right">
                                        <h4><Link to="#">Print Collections</Link></h4>
                                        <p>Oct 14, 2023</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-3 col-xs-3 col-sm-6">
                            <div className="single-footer-widget">
                                <h3>main links</h3>
                                <ul className="quicklinks">
                                    <li><Link to="/"><i className="fa fa-angle-double-right "></i> About us</Link></li>
                                    <li><Link to="/"><i className="fa fa-angle-double-right "></i> Delivery Information</Link></li>
                                    <li><Link to="/"><i className="fa fa-angle-double-right "></i> Contact with an expert</Link></li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-lg-3 col-xs-3 col-sm-6">
                            <div className="single-footer-widget">
                                <h3>Contact Info</h3>
                                <p><i className="fa fa-map-marker"></i> Anywhere in the world </p>
                                <p><i className="fa fa-phone"></i>1 (800) 555 5555</p>
                                <p><i className="fa fa-envelope-o"></i> contact@lms.com</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
};

export default Footer;