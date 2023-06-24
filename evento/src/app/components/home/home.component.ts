import { Component  ,ElementRef, Renderer2, AfterViewInit, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit ,OnInit{

  userId:any
  constructor(private elRef: ElementRef, private renderer: Renderer2) {
  }
  ngOnInit(): void {
    
  }
  
   ngAfterViewInit() {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          const section = entry.target;
          this.renderer.addClass(section, 'animate');
        }
      });
    });
    
    const sections = this.elRef.nativeElement.querySelectorAll('section');
    sections.forEach((section:any) => {
      this.renderer.addClass(section, 'left-animation');
      observer.observe(section);
    });
  }
}