import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";
import {Purchase} from "./purchase";
import {IHash} from "./hash";

@Component({
  selector: 'app-scratch',
  templateUrl: './scratch.component.html',
  styleUrls: ['./scratch.component.css']
})
export class ScratchComponent implements OnInit {

  users: User[] = [];
  purchases: Purchase[] = [];

  newPurchase: Purchase = new Purchase();

  sums : IHash = {};

  constructor( private httpClient:HttpClient) { }

  ngOnInit() {
    this.loadData();

  }

  loadPurchases(){
    this.httpClient.get<Purchase[]>("api/purchases")
      .subscribe(resp => {
        this.purchases = resp;
        this.calculateSumsForUsers();
      });
  }

  public onChange(event): void {
    const id = event.target.value;
    this.newPurchase.user = this.users.find(x => x.id == id);
  }

  loadUsers() {
    this.httpClient.get<User[]>("api/users")
    //NOTE: ideally, we should have an error handler here, which we left away for simplicity
      .subscribe(resp => {
        this.users = resp;
        this.newPurchase.user = this.users[0];
      });
  }

  private loadData() {
    this.loadUsers();
    this.loadPurchases();
  }

  createPurchase() {
    this.httpClient.post("api/purchases", this.newPurchase)
      .subscribe(() => {
        this.loadPurchases();
        const u = this.newPurchase.user;
        this.newPurchase = new Purchase();
        this.newPurchase.user = u;
      });
  }

  deletePurchase(id: number) {
    this.httpClient.delete("api/purchases/" + id).subscribe(() => this.loadPurchases());
  }

  sum() {
    if (this.purchases.length){
      return this.purchases.map(p => p.price).reduce((a, b) => a + b);
    }
  }

  calculateSumsForUsers(){
    this.sums = {};
    if (this.purchases.length){
      this.purchases.map(p => {
        if (!isNaN(this.sums[p.user.username])){
          this.sums[p.user.username] = this.sums[p.user.username] + p.price;
        } else {
          this.sums[p.user.username] = p.price;
        }
      })
    }
  }
}
