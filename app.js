// Admin table filtering and status doughnut chart (no third-party chart library)
document.addEventListener('DOMContentLoaded',()=>{
 const search=document.querySelector('#adminSearch');
 if(search) search.addEventListener('input',()=>{
  const term=search.value.toLowerCase();
  document.querySelectorAll('#complainttable tbody tr').forEach(row=>{
   row.style.display=row.innerText.toLowerCase().includes(term)?'':'none';
  });
 });
 const canvas=document.getElementById('statusChart');
 if(canvas && window.vStatus){
  const ctx=canvas.getContext('2d');const vals=window.vStatus.map(v=>Math.max(0,Number(v)||0));
  const total=vals.reduce((a,b)=>a+b,0);const colors=['#e6a354','#7889e2','#47b38a'];
  const cx=canvas.width/2,cy=canvas.height/2,r=88,inner=57;
  if(total===0){ctx.beginPath();ctx.arc(cx,cy,r,0,Math.PI*2);ctx.arc(cx,cy,inner,0,Math.PI*2,true);ctx.fillStyle='#edf1f6';ctx.fill();ctx.fill();ctx.font='14px sans-serif';ctx.fillStyle='#8593a6';ctx.textAlign='center';ctx.fillText('No data',cx,cy+5);}
  else{let angle=-Math.PI/2;vals.forEach((v,i)=>{let next=angle+(v/total)*Math.PI*2;ctx.beginPath();ctx.arc(cx,cy,r,angle,next);ctx.arc(cx,cy,inner,next,angle,true);ctx.closePath();ctx.fillStyle=colors[i];ctx.fill();angle=next;});ctx.font='bold 25px sans-serif';ctx.fillStyle='#243c58';ctx.textAlign='center';ctx.textBaseline='middle';ctx.fillText(total,cx,cy-4);ctx.font='12px sans-serif';ctx.fillStyle='#8a97a9';ctx.fillText('complaints',cx,cy+19);}
 }
});
